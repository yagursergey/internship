package com.syagur.repository;

import com.syagur.entity.Realty;
import com.syagur.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("/application-test.properties")
public class RealtyRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RealtyRepository realtyRepository;

    private static final Long USER_ID = 2L;

    private Sort sort = Sort.by(Sort.Direction.ASC, "id");

    @Test
    public void whenFindByOwnerAndSort_thenReturnListOfRealties() {
        User owner = new User();
        owner.setId(USER_ID);

        Optional<List<Realty>> foundRealties = realtyRepository.findByOwnerAndIsDeletedFalse(owner, sort);
        assertNotNull(foundRealties);

    }

    @Test
    public void whenFindByIsDeletedFalse_thenReturnListOfRealties() {

        List<Realty> foundRealties = realtyRepository.findByIsDeletedFalse(sort);

        assertFalse(foundRealties.get(0).isDeleted());

    }

    @Test
    public void whenFindByIsDeletedTrue_thenReturnListOfRealties() {

        List<Realty> foundRealties = realtyRepository.findByIsDeletedTrue(sort);

        assertTrue(foundRealties.get(0).isDeleted());
    }

}
