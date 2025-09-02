package org.top.myspringwebusers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.top.myspringwebusers.user.User;
import org.top.myspringwebusers.user.UserRepository;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired private UserRepository repo;

    @Test
    public void testAddNew() {
        User user = new User();
        user.setEmail("oleg@mail.ru");
        user.setPassword("157");
        user.setFirstName("Oleg");
        user.setLastName("Bunker");

        User savedUser = repo.save(user);

        Assertions.assertNotNull(savedUser);
        Assertions.assertTrue(savedUser.getId() > 0);
    }

    @Test
    public void testListAll() {
        Iterable<User> users = repo.findAll();
        Assertions.assertTrue(users.spliterator().getExactSizeIfKnown() > 0);

        for (User user: users) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate() {
        Integer userId = 5;
        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertNotNull(optionalUser);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword("111");
            repo.save(user);

            Assertions.assertEquals(user.getPassword(), "111");
        }
    }

    @Test
    public void testGet() {
        Integer userId = 2;
        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertTrue(optionalUser.isPresent());
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete() {
        Integer userId = 5;
        repo.deleteById(userId);

        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertTrue(optionalUser.isEmpty());
    }
}
