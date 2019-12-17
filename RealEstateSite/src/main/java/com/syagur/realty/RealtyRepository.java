package com.syagur.realty;

import com.syagur.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RealtyRepository extends JpaRepository<Realty, Long> {

    Page<Realty> findAll(Pageable pageable);

    Optional<List<Realty>> findByOwnerAndIsDeletedFalse(User owner, Sort sort);

    Optional<List<Realty>> findByIsDeletedFalse(Sort sort);

    Optional<List<Realty>> findByIsDeletedTrue(Sort sort);
}
