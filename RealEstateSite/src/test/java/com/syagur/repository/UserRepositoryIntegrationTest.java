package com.syagur.repository;

import com.syagur.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.properties")
public class UserRepositoryIntegrationTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    private User user;

    @Test
    public void whenFindUserByEmail_thenReturnUser() {
        user = new User();
        user.setEmail("user#TEST@mail.com");

        Optional<User> optionalUser = Optional.of(user);

        Optional<User> foundUser = userRepository.findByEmail("user#TEST@mail.com");

        String emailOfFoundUser = foundUser.get().getEmail();

        assertThat(emailOfFoundUser)
                .isEqualTo(this.user.getEmail());
    }
}