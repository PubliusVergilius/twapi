package com.vini.dev.twapi.api.users.service;

import com.vini.dev.twapi.api.users.domain.User;
import jakarta.transaction.Transactional;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;



@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    static final class Template {
        String name;
        User user;
        boolean shouldError;

        public Template(final String name, final User user, final boolean shouldError) {
            this.name = name;
            this.user = user;
            this.shouldError = shouldError;
        }
    }

    @Test
    void it_should_create_users () {
        final List<Template> table = new ArrayList<>();
        table.add(new Template("Should save first user", new User("user_1"), false));
        table.add(new Template("Should save second user", new User("user_2"), false));
        table.add(new Template("Should fail on saving duplicated username", new User("user_2"), true));

        table.forEach(test -> {
            try {
                final User want = new User(test.user.getUsername());
                final User got = this.userService.registerUser(want);
                want.setId(got.getId());

                Assertions.assertNotNull(got, test.name);
                Assertions.assertEquals(want, got, test.name);
            } catch (final Exception e) {
                Assertions.assertInstanceOf(DataIntegrityViolationException.class, e, test.name);
            }
        });
    }
}
