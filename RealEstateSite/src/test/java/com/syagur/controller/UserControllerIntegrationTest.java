package com.syagur.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.properties")
@WithMockUser(username = "admin#TEST@mail.com", authorities = "ADMIN")
public class UserControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserController userController;

    private static final int LIST_SIZE = 2;
    private static final String PATH = "/users";
    private static final String EMAIL = "admin#TEST@mail.com";

    @Test
    void assertThatControllerIsNotNull() {
        assertThat(this.userController).isNotNull();
    }

    @Test
    public void givenAllUsers_whenGetAllUsers_thenReturnJsonArray() throws Exception {
        this.mockMvc.perform(get(PATH))
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(LIST_SIZE)));
    }

    @Test
    public void givenUserById_whenGetUserById_thenReturnJsonArray() throws Exception {
        this.mockMvc.perform(get(PATH + "/1"))
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", Matchers.is(EMAIL)));
    }


}
