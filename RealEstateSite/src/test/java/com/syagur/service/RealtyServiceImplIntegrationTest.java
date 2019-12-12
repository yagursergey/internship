package com.syagur.service;

import com.syagur.entity.Realty;
import com.syagur.repository.RealtyRepository;
import com.syagur.service.impl.RealtyServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RealtyServiceImplIntegrationTest {

    @Autowired
    private RealtyService realtyService;
    @MockBean
    private RealtyRepository realtyRepository;

    @Test
    public void whenFindById_thenRealtyShouldBeFound() {
    }

    @Test
    public void whenGetAllDeleted_thenAllDeletedRealtiesShouldBeFound() {
    }

    @Test
    public void whenFindByOwnerAndSort_thenAllUsersRealtiesShouldBeFound() {
    }

    @Test
    public void whenUndelete_thenRealtyShouldBeUndeleted() {
    }

    @Test
    public void whenFindAllNotDeleted_thenAllNotDeletedRealtiesShouldBeFound() {
    }

    @Test
    public void whenSaveNewRealty_thenRealtyShouldBeSaved() {
    }

    @TestConfiguration
    static class RealtyServiceImplTestContextConfiguration {

        @Bean
        public RealtyService realtyService() {
            return new RealtyServiceImpl();
        }
    }
//
//    @Test
//    public void () {}
//
//    @Test
//    public void () {}
//
//    @Test
//    public void () {}
//
//    @Test
//    public void () {}
}
