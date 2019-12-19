package com.syagur.repository;

import com.syagur.realty.Realty;
import com.syagur.realty.RealtyRepository;
import com.syagur.user.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("/application-test.yml")
public class RealtyRepositoryIntegrationTest {

    private static final boolean DELETED = true;
    private static final Integer SIZE = 10;
    private static final Long USER_ID = 2L;
    private static final Pageable PAGEABLE = PageRequest
            .of(0, 10, Sort.by(Sort.Direction.ASC, "id"));
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private RealtyRepository realtyRepository;

    @Test
    public void whenFindByOwnerAndSort_thenReturnListOfRealties() {
        User owner = new User();
        owner.setId(USER_ID);

        Optional<Page<Realty>> foundRealtiesOptional = realtyRepository.findByOwnerAndIsDeletedFalse(owner, PAGEABLE);
        assertTrue(foundRealtiesOptional.isPresent());

        Page<Realty> foundRealties = foundRealtiesOptional.get();
        assertThat(SIZE).isEqualTo(foundRealties.getSize());
        assertTrue(foundRealties.hasContent());

    }

    @Test
    public void whenFindByIsDeletedFalse_thenReturnListOfRealties() {

        Optional<Page<Realty>> foundRealtiesOptional = realtyRepository.findByIsDeletedFalse(PAGEABLE);
        assertTrue(foundRealtiesOptional.isPresent());

        Page<Realty> foundRealties = foundRealtiesOptional.get();
        assertThat(SIZE).isEqualTo(foundRealties.getSize());
        assertTrue(foundRealties.hasContent());

        assertFalse(foundRealties.getContent().get(0).isDeleted());

    }

    @Test
    public void whenFindByIsDeletedTrue_thenReturnListOfRealties() {

        Optional<Page<Realty>> foundRealtiesOptional = realtyRepository.findByIsDeletedFalse(PAGEABLE);
        assertTrue(foundRealtiesOptional.isPresent());

        Page<Realty> foundRealties = foundRealtiesOptional.get();
        assertThat(SIZE).isEqualTo(foundRealties.getSize());
        assertTrue(foundRealties.hasContent());

        foundRealties.getContent().get(0).setDeleted(DELETED);
        assertTrue(foundRealties.getContent().get(0).isDeleted());
    }
}
