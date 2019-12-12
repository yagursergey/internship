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
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.properties")
public class UserRepositoryIntegrationTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    private static final String USER_EMAIL = "user#TEST@mail.com";


    @Test
    public void whenFindUserByEmail_thenReturnUser() {

        Optional<User> foundUser = userRepository.findByEmail(USER_EMAIL);

        assertTrue(foundUser.isPresent());
        assertThat(USER_EMAIL)
                .isEqualTo(foundUser.get().getEmail());
    }
}