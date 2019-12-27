package com.syagur.service;

import com.syagur.user.User;
import com.syagur.user.UserRepository;
import com.syagur.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplIntegrationTest {

    private static final Pageable PAGEABLE = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));
    private static final Long ADMIN_ID = 1L;
    private static final Long USER_ID = 2L;
    private static final Long TOTAL_ELEMENTS = 2L;
    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    @Test
    public void whenFindAll_thenAllUsersShouldBeFound() {
        Page<User> userList = getUserList();

        when(userRepository.findAll(PAGEABLE)).thenReturn(userList);

        Page<User> users = userService.findAll(PAGEABLE);

        assertTrue(users.hasContent());
        assertThat(users.getTotalElements())
                .isEqualTo(TOTAL_ELEMENTS);

        assertThat(users.getContent().get(0).getId())
                .isEqualTo(ADMIN_ID.intValue());
        assertThat(users.getContent().get(1).getId())
                .isEqualTo(USER_ID.intValue());
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

    private Page<User> getUserList() {
        List<User> users = new ArrayList<>();

        User user1 = new User();
        user1.setId(ADMIN_ID);

        User user2 = new User();
        user2.setId(USER_ID);

        users.add(user1);
        users.add(user2);
        return new PageImpl<>(users);
    }
}
