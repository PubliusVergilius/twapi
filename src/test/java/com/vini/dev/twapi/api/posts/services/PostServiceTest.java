package com.vini.dev.twapi.api.posts.services;

import com.vini.dev.twapi.api.posts.domain.Post;
import com.vini.dev.twapi.api.posts.dto.PostCreateDTO;
import com.vini.dev.twapi.api.posts.dto.PostDTO;
import com.vini.dev.twapi.api.posts.exceptions.InexistentUserException;
import com.vini.dev.twapi.api.users.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;


/// Using h2 database instead of the stub give a more realistic scenario
///
/// @Deprecated
/// @Import(StubRepositoryConfig.class) // import test config with stub
@SpringBootTest
public class PostServiceTest {

	/// Using h2 database instead of the stub give a more realistic scenario
	///@Deprecated
	///@Autowired
	///private StubPostRepository stubPostRepository;
	///@Deprecated
	///@BeforeEach
	///public  void setUp () {
	///stubPostRepository.clear();
	///stubPostRepository.preload(new Post("1", "1", "teste"));
	///}

	@Autowired
	private PostService postService;

	@Test
	void it_should_create_post () {
		// Test cases
		// The user insert when spring starts first
		var defaultUser = new User("developer");
		defaultUser.setId("1");


		List<Template> test_table = new ArrayList<>();
		test_table.add(new Template("should save and return first post", new Post("1",
				defaultUser, "teste 1"), false));
		test_table.add(new Template("should save and return second post", new Post("2",
				defaultUser, "teste 2"), false));


		// Test validation
		test_table.forEach(test -> {
			String id = test.want.getId();
			Post want = new Post(id, defaultUser, test.want.getBody());

			var got = this.postService.registerPost(new PostCreateDTO(want.getAuthor().getId(), want.getBody()));
			Assertions.assertNotNull(got, "should never be null");
			Assertions.assertEquals(want.getBody(), got.body(), "wrong response body on registering new post");
			Assertions.assertEquals(want.getAuthor().getId(), got.userId(), "wrong user id on " +
					"registering new post");

		});
	}

	@Test
	void should_throw_on_creating_post_with_inexisting_user () {
		Assertions.assertThrows(InexistentUserException.class, () -> {
			postService.registerPost(new PostCreateDTO("2", "teste falho"));
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
