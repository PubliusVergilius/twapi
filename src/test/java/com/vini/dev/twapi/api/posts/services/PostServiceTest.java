package com.vini.dev.twapi.api.posts.services;

import com.vini.dev.twapi.api.posts.domain.Post;
import com.vini.dev.twapi.api.posts.dto.PostCreateDTO;
import com.vini.dev.twapi.api.posts.dto.PostDTO;
import com.vini.dev.twapi.api.posts.exceptions.InexistentUserException;
import com.vini.dev.twapi.api.users.domain.User;
import com.vini.dev.twapi.api.users.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;


/// Using h2 database instead of the stub give a more realistic scenario
///
/// @Deprecated
/// @Import(StubRepositoryConfig.class) // import test config with stub
@SpringBootTest
// @ActiveProfiles("test")
@Transactional
public class PostServiceTest {

	/// Using h2 database instead of the stub give a more realistic scenario
	///@Deprecated
	///@Autowired
	///private StubPostRepository stubPostRepository;
	///@Deprecated
	///@BeforeEach
	///public  void setUp () {
	///  stubPostRepository.clear();
	///  stubPostRepository.preload(new Post("1", "1", "teste"));
	///}

	@Autowired
	private PostService postService;

	private User testUser1;
	private User testUser2;

	@Autowired
	private UserRepository userRepository;

	PostServiceTest() {

	}

	@Test
	void should_create_new_post () {
		// Preloaded test users
		testUser1 = new User();
		testUser2 = new User();

		testUser1.setUsername("user-1");
		testUser1.setId("1");

		testUser1.setUsername("user-2");
		testUser2.setId("2");

		List<Template> test_table = new ArrayList<>();
		test_table.add(new Template("should save and return first post", new Post("1",
				"test post from user 1", testUser1), false));
		test_table.add(new Template("should save and return second post", new Post("2",
				"test post from user 2", testUser2), false));

		// Test validation
		test_table.forEach(test -> {
			String id = test.want.getId();
			Post want = new Post(id, "teste post", test.want.getAuthor());

			var got = this.postService.registerPost(new PostCreateDTO(want.getAuthor().getId(), want.getBody()));
			Assertions.assertTrue(got.isPresent(), "should never be null");
			Assertions.assertEquals(want.getBody(), got.get().getBody(), "wrong response body on" +
					" " +
					"registering new post");
			Assertions.assertEquals(want.getAuthor().getId(), got.get().getAuthor().getId(),
					"wrong user " +
					"id on " +
					"registering new post");
		});
	}

	@Test
	void should_thrown_on_creating_post_with_inexisting_user () {
		var inexistentUser = new User("inexistent");
		inexistentUser.setId("123456");
		Assertions.assertThrows(InexistentUserException.class, () -> {
			final var testPost = new PostCreateDTO(inexistentUser.getId(), "teste falho");
			this.postService.registerPost(testPost);
		});
	}

	@Test
	void should_retrieve_post_by_id () {
		final var got = this.postService.retrievePost("1");
		Assertions.assertTrue(got.isPresent(), "post should not be null");
		Assertions.assertEquals(new PostDTO("1", "1", "teste 1"), got.get());
	}

	class Template {
		String message;
		Post want;
		boolean shouldError;

		public Template (final String message, final Post want, final boolean shouldError) {
			this.message = message;
			this.want = want;
			this.shouldError = shouldError;
		}
	}

}
