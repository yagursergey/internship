package com.syagur.service;

import com.syagur.entity.User;
import com.syagur.repository.UserRepository;
import com.syagur.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplIntegrationTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {

        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }

    }

    private static final Sort SORT = Sort.by(Sort.Direction.ASC, "id");
    private static final Long ADMIN_ID = 1L;
    private static final Long USER_ID = 2L;

    @Test
    public void whenFindAll_thenAllUsersShouldVeFound() {
        List<User> userList = getUserList();

        when(userRepository.findAll(SORT)).thenReturn(userList);

        List<User> users = userService.findAll(SORT);

        assertNotNull(users);
        assertThat(userList.size())
                .isEqualTo(users.size());
        assertThat(users.get(0).getId())
                .isEqualTo(ADMIN_ID);
        assertThat(users.get(1).getId())
                .isEqualTo(USER_ID);
    }

    @Test
    public void whenFindUserById_thenUserShouldBeFound() {
        User user = new User();
        user.setId(USER_ID);

        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));

        User userFound = userService.findById(USER_ID);

        assertNotNull(user);
        assertThat(USER_ID)
                .isEqualTo(userFound.getId());

    }

    private List<User> getUserList() {
        List<User> users = new ArrayList<>();

        User user1 = new User();
        user1.setId(ADMIN_ID);

        User user2 = new User();
        user2.setId(USER_ID);

        users.add(user1);
        users.add(user2);
        return users;
    }
}
