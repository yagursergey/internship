package com.syagur.service;

import com.syagur.realty.Realty;
import com.syagur.realty.RealtyRepository;
import com.syagur.realty.RealtyService;
import com.syagur.user.User;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RealtyServiceImplIntegrationTest {

    private static final Integer TOTAL_ELEMENTS = 1;
    private static final Long USER_ID = 2L;
    private static final Long REALTY_ID = 1L;
    private static final Pageable PAGEABLE = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));
    private static final boolean DELETED = true;

    @Autowired
    private RealtyService realtyService;
    @MockBean
    private RealtyRepository realtyRepository;

    @Test
    public void whenFindAllNotDeleted_thenAllNotDeletedRealtiesShouldBeFound() {
        Optional<Page<Realty>> realtyListOptional = getRealtyOptionalPage();

        when(realtyRepository.findByIsDeletedFalse(PAGEABLE)).thenReturn(realtyListOptional);

        Page<Realty> realties = realtyService.findAllNotDeleted(PAGEABLE);
        assertThat(TOTAL_ELEMENTS).isEqualTo(realties.getTotalElements());
        assertTrue(realties.hasContent());

        assertFalse(realties.getContent().get(0).isDeleted());
    }

    @Test
    public void whenFindByOwnerAndSort_thenAllUsersRealtiesShouldBeFound() {
        Optional<Page<Realty>> realtyList = getRealtyOptionalPage();
        User owner = new User();
        owner.setId(USER_ID);

        when(realtyRepository.findByOwnerAndIsDeletedFalse(owner, PAGEABLE)).thenReturn(realtyList);

        Page<Realty> realties = realtyService.findByOwner(owner, PAGEABLE);
        assertThat(TOTAL_ELEMENTS).isEqualTo(realties.getTotalElements());
        assertTrue(realties.hasContent());

        assertFalse(realties.getContent().get(0).isDeleted());

        Long userId = realties.getContent().get(0).getOwner().getId();
        assertThat(USER_ID).isEqualTo(userId);
    }

    @Test
    public void whenGetAllDeleted_thenAllDeletedRealtiesShouldBeFound() {
        Optional<Page<Realty>> realtyList = getRealtyOptionalPage();
        realtyList.get().getContent().get(0).setDeleted(DELETED);

        when(realtyRepository.findByIsDeletedTrue(PAGEABLE)).thenReturn(realtyList);

        Page<Realty> realties = realtyService.getAllDeleted(PAGEABLE);
        assertThat(TOTAL_ELEMENTS).isEqualTo(realties.getTotalElements());
        assertTrue(realties.hasContent());

        assertTrue(realties.getContent().get(0).isDeleted());
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

    private Optional<Page<Realty>> getRealtyOptionalPage() {
        List<Realty> realtyList = new ArrayList<>();
        Realty realty = new Realty();

        User owner = new User();
        owner.setId(USER_ID);

        realty.setId(REALTY_ID);
        realty.setOwner(owner);

        realtyList.add(realty);

        return Optional.of(new PageImpl<>(realtyList));
    }
}
