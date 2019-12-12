package com.syagur.service;

import com.syagur.entity.Realty;
import com.syagur.entity.User;
import com.syagur.repository.RealtyRepository;
import com.syagur.service.impl.RealtyServiceImpl;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RealtyServiceImplIntegrationTest {

    @Autowired
    private RealtyService realtyService;

    @MockBean
    private RealtyRepository realtyRepository;

    @TestConfiguration
    static class RealtyServiceImplTestContextConfiguration {

        @Bean
        public RealtyService realtyService() {
            return new RealtyServiceImpl();
        }

    }

    private static final Long USER_ID = 2L;
    private static final Long REALTY_ID = 1L;
    private static final boolean REALTY_IS_DELETED = true;
    private static final boolean REALTY_IS_NOT_DELETED = false;

    private static final Sort SORT = Sort.by(Sort.Direction.ASC, "id");

    @Test
    public void whenFindAllNotDeleted_thenAllNotDeletedRealtiesShouldBeFound() {
        List<Realty> realtyList = getRealtyList();
        realtyList.get(0).setDeleted(REALTY_IS_NOT_DELETED);

        when(realtyRepository.findByIsDeletedFalse(SORT)).thenReturn(realtyList);

        List<Realty> realties = realtyService.findAllNotDeletedAndSort(SORT);

        assertThat(realtyList.size())
                .isEqualTo(realties.size());
        assertFalse(realties.get(0).isDeleted());
    }

    @Test
    public void whenFindByOwnerAndSort_thenAllUsersRealtiesShouldBeFound() {
        List<Realty> realtyList = getRealtyList();
        User owner = new User();
        owner.setId(USER_ID);

        when(realtyRepository.findByOwnerAndIsDeletedFalse(owner, SORT)).thenReturn(Optional.of(realtyList));

        List<Realty> realties = realtyService.findByOwnerAndSort(owner, SORT);

        assertThat(realtyList.size())
                .isEqualTo(realties.size());
        assertFalse(realties.get(0).isDeleted());
        assertThat(USER_ID)
                .isEqualTo(realties.get(0).getOwner().getId());
    }

    @Test
    public void whenGetAllDeleted_thenAllDeletedRealtiesShouldBeFound() {
        List<Realty> realtyList = getRealtyList();
        realtyList.get(0).setDeleted(REALTY_IS_DELETED);

        when(realtyRepository.findByIsDeletedTrue(SORT)).thenReturn(realtyList);

        List<Realty> realties = realtyService.getAllDeleted(SORT);

        assertThat(realtyList.size())
                .isEqualTo(realties.size());
        assertTrue(realties.get(0).isDeleted());
    }

    @Test
    public void whenFindById_thenRealtyShouldBeFound() {
        Realty realty = new Realty();
        realty.setId(REALTY_ID);

        when(realtyRepository.findById(REALTY_ID)).thenReturn(Optional.of(realty));

        Realty realtyFound = realtyService.findById(REALTY_ID);

        assertThat(REALTY_ID)
                .isEqualTo(realtyFound.getId());
    }

    private List<Realty> getRealtyList() {
        List<Realty> realtyList = new ArrayList<>();
        Realty realty = new Realty();

        User owner = new User();
        owner.setId(USER_ID);

        realty.setId(REALTY_ID);
        realty.setOwner(owner);

        realtyList.add(realty);

        return realtyList;
    }
}
