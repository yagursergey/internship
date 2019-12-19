package com.syagur.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syagur.realty.RealtyController;
import com.syagur.realty.RealtyDto;
import com.syagur.realty.RealtyType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.yml")
@WithMockUser(username = "user#TEST@mail.com", authorities = "USER")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class RealtyControllerIntegrationTest {

    private static final int LIST_SIZE = 1;
    private static final String PATH = "/realties";
    private static final int ID_OF_DELETED_REALTY = 2;
    private static final int ID_OF_NOT_DELETED_REALTY = 1;
    private static final String OWNER_FIRST_NAME = "Fname of User#TEST";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RealtyController realtyController;

    @Test
    void assertThatControllerIsNotNull() {
        assertThat(this.realtyController).isNotNull();
    }

    @Test
    void givenAllRealties_whenGetAllRealties_thenReturnJsonArray() throws Exception {

        this.mockMvc.perform(get(PATH))
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id", Matchers.is(ID_OF_NOT_DELETED_REALTY)))
                .andExpect(jsonPath("$.content", hasSize(LIST_SIZE)));

    }

    @Test
    void givenMyRealties_whenGetMyRealties_thenReturnJsonArray() throws Exception {

        this.mockMvc.perform(get(PATH + "/my"))
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id", Matchers.is(ID_OF_NOT_DELETED_REALTY)))
                .andExpect(jsonPath("$.content", hasSize(LIST_SIZE)))
                .andExpect(jsonPath("$.content[0].ownerFirstName", Matchers.is(OWNER_FIRST_NAME)));
    }

    @Test
    @WithMockUser(username = "admin#TEST@mail.com", authorities = "ADMIN")
    void givenDeletedRealties_whenGetDeletedRealties_thenReturnJsonArray() throws Exception {

        this.mockMvc.perform(get(PATH + "/deleted"))
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id", Matchers.is(ID_OF_DELETED_REALTY)))
                .andExpect(jsonPath("$.content", hasSize(LIST_SIZE)));

    }

    @Test
    public void givenCreateNewRealty_whenPostCreateNewRealty_thenCreateNewRealty() throws Exception {

        RealtyDto realtyDto = new RealtyDto();
        realtyDto.setPrice(12.0);
        realtyDto.setDescription("description");
        realtyDto.setDateOfCreation(LocalDate.now().toString());
        realtyDto.setSquare(200.0);
        realtyDto.setType(RealtyType.LAND.toString());

        this.mockMvc.perform(post(PATH)
                .content(asJsonString(realtyDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(authenticated())
                .andExpect(status().isCreated());
    }

    @Test
    void givenRealtyById_whenGetRealtyById_thenReturnJsonArray() throws Exception {

        this.mockMvc.perform(get(PATH + "/1"))
                .andExpect(authenticated())
                .andExpect(jsonPath("$.id", Matchers.is(ID_OF_NOT_DELETED_REALTY)))
                .andExpect(status().isOk());
    }

    @Test
    void givenPatchRealtyById_whenPatchRealtyById_thenPatchRealty() throws Exception {

        RealtyDto realtyDto = new RealtyDto();
        realtyDto.setPrice(12.0);
        realtyDto.setDescription("description");
        realtyDto.setSquare(200.0);
        realtyDto.setType(RealtyType.LAND.toString());

        this.mockMvc.perform(patch(PATH + "/1")
                .content(asJsonString(realtyDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(authenticated())
                .andExpect(status().is(204));
    }

    @Test
    void givenDeleteRealtyById_whenDeleteRealtyById_thenSoftDeleteRealty() throws Exception {

        this.mockMvc.perform(delete(PATH + "/2"))
                .andExpect(authenticated())
                .andExpect(status().is(204));
    }

    @Test
    @WithMockUser(username = "admin#TEST@mail.com", authorities = "ADMIN")
    void givenDeletedRealtyById_whenPatchDeletedRealtyById_thenSoftUndeleteRealty() throws Exception {

        this.mockMvc.perform(patch(PATH + "/deleted/2"))
                .andExpect(authenticated())
                .andExpect(status().is(204));
    }

    private String asJsonString(RealtyDto realtyDto) {
        try {
            return new ObjectMapper().writeValueAsString(realtyDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
