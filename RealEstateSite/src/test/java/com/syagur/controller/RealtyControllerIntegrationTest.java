package com.syagur.controller;

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
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.properties")
public class RealtyControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RealtyController realtyController;

    private static final int LIST_SIZE = 1;
    private static final boolean IS_DELETED = true;
    private static final boolean NOT_DELETED = false;

    @Test
    void assertThatControllerIsNotNull() {
        assertThat(this.realtyController).isNotNull();
    }

    @Test
    @WithMockUser("user#TEST@mail.com")
    void givenAllRealties_whenGetAllRealties_thenReturnJsonArray() throws Exception {

        this.mockMvc.perform(get("/realties"))
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(LIST_SIZE)));

    }

//    Todo fix 403 code
    @Test
    @WithMockUser("user#TEST@mail.com")
    void givenDeletedRealties_whenGetDeletedRealties_thenReturnJsonArray() throws Exception {

        this.mockMvc.perform(get("/realties/deleted"))
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(LIST_SIZE)));

    }


}
