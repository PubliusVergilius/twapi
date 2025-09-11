package com.vini.dev.twapi.api.users.service;

import com.vini.dev.twapi.api.domain.UserStore;
import com.vini.dev.twapi.api.posts.services.PostService;
import com.vini.dev.twapi.api.users.domain.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;


@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

	@MockBean
	private UserStore userStore;
	@MockBean
	private PostService postService;


	static final class Template <T> {
        String name;
        UserServiceResult<T> result;
        boolean shouldError;

        public Template(final String name, final UserServiceResult<T> result,
                        final boolean shouldError) {
            this.name = name;
            this.result = result;
            this.shouldError = shouldError;
        }
    }

	@Test
	void should_return_two_users_on_retriveAllUsers() {
		User user1 = new User("user1");
		user1.setId("1");
		User user2 = new User("user2");
		user2.setId("2");
		Mockito.when(userStore.findAll()).thenReturn(List.of(user1, user2));

		final UserServiceResult<List<User>> want =
				new UserServiceResult(List.of(user1, user2), null);
		var got = userService.retrieveAllUsers();

		Assertions.assertTrue(got.get().size() == want.get().size(), "Should contain 2 " +
				"users");
		Assertions.assertNull(got.error());
		Assertions.assertEquals(want.get(), got.get(), "Want does not match with got");
	}

	@Test
	void should_return_error_message_on_retriveAllUsers () {
		Mockito.when(userStore.findAll()).thenReturn(List.of());
		UserServiceResult<List<User>> got = userService.retrieveAllUsers();

		Assertions.assertNull(got.get());
		Assertions.assertTrue(got.isError());
		Assertions.assertEquals(UserServiceErrorMessage.noUserFound(), got.error());
	}

	@Test
	void should_retrive_user_by_id_on_retriveUserById() {
		User want = new User("user1");
		want.setId("1");

		Mockito.when(userStore.findById(want.getId())).thenReturn(Optional.of(want));


		UserServiceResult<User> got = userService.retrieveUserById(want.getId());

		Assertions.assertNull(got.error());

		Assertions.assertEquals(want, got.get());
	}


	@Test
	void should_return_error_message_on_retriveUserById() {
		var inexistentUserId = "111999";
		var got = userService.retrieveUserById(inexistentUserId);

		Assertions.assertTrue(got.isError());
		Assertions.assertNull(got.get());
		Assertions.assertEquals(got.error(), UserServiceErrorMessage.inexistentUser(),
				"Inexistent user message error expected");
	}

    @Test
    void should_create_users_on_registerUser () {
        final List<Template> table = new ArrayList<>();
		var test1 = new UserServiceResult(new User("user_1"), null);
	    var test2 = new UserServiceResult(new User("user_1"), null);

        table.add(new Template("Should save first user", test1, false));
        table.add(new Template("Should save second user", test2, false));
        table.add(new Template("Should fail on saving duplicated username", test2, true));

	    AtomicInteger tableIndex = new AtomicInteger(1);
        table.forEach(test -> {
	        int _index = tableIndex.getAndIncrement();

            try {
	            final User testUser = (User) test.result.get();

				final User want = (User) test.result.get();
				String testId = String.valueOf(_index);
				want.setId(testId);

				Mockito.when(userStore.save(testUser)).thenReturn(want);

				User got = userService.registerUser(testUser);

                Assertions.assertNotNull(got, test.name);
                Assertions.assertEquals(want.getUsername(), got.getUsername(), test.name);
            } catch (final Exception e) {
                Assertions.assertInstanceOf(DataIntegrityViolationException.class, e, test.name);
            }
        });
    }
}
