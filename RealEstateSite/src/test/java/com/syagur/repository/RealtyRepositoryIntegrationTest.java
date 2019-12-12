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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("/application-test.properties")
public class RealtyRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RealtyRepository realtyRepository;

    private User owner;
    private Realty realty;
    private Sort sort;

    public void beforeTest() {
        realty = new Realty();
        realty.setId(1L);

        owner = new User();
        owner.setId(2L);
        owner.setEmail("user#TEST@mail.com");

        realty.setOwner(owner);

    }

    @Test
    public void whenFindByOwnerAndSort_thenReturnListOfRealties() {

        beforeTest();

        sort = Sort.by("price");

        Optional<List<Realty>> foundRealties = realtyRepository.findByOwnerAndIsDeletedFalse(owner, sort);
        Long idOfFoundRealty = foundRealties.get().get(0).getId();
        assertThat(idOfFoundRealty)
                .isEqualTo(this.realty.getId());
    }

    @Test
    public void whenFindByIsDeletedFalse_thenReturnListOfRealties() {

        beforeTest();

        sort = Sort.by(Sort.Direction.ASC, "id");

        List<Realty> realties = new ArrayList<>();
        realties.add(realty);

        List<Realty> foundRealties = realtyRepository.findByIsDeletedFalse(sort);

        assertThat(getOneAndReturnIsDeleted(foundRealties))
                .isEqualTo(getOneAndReturnIsDeleted(realties));
    }

    @Test
    public void whenFindByIsDeletedTrue_thenReturnListOfRealties() {

        beforeTest();

        sort = Sort.by(Sort.Direction.ASC, "id");

        List<Realty> realties = new ArrayList<>();
        this.realty.setDeleted(true);
        realties.add(realty);

        List<Realty> foundRealties = realtyRepository.findByIsDeletedTrue(sort);

        assertThat(getOneAndReturnIsDeleted(foundRealties))
                .isEqualTo(getOneAndReturnIsDeleted(realties));
    }

    private boolean getOneAndReturnIsDeleted(List<Realty> realtyList) {
        return realtyList.stream().map(Realty::isDeleted).collect(Collectors.toList()).get(0);
    }
}
