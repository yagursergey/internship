package com.syagur.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.properties")
public class RealtyControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RealtyController realtyController;

    @Test
    void givenRealties_whenGetRealties_thenReturnJsonArray() throws Exception {
        assertThat(this.realtyController).isNotNull();
        this.mockMvc.perform(get("/realties"))
                .andExpect(status().isOk());
    }
}
