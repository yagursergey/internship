package com.syagur.repository;

import com.syagur.entity.Realty;
import com.syagur.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RealtyRepository extends JpaRepository<Realty, Long> {

    Optional<List<Realty>> findByOwnerAndIsDeletedFalse(User owner, Sort sort);

    List<Realty> findByIsDeletedFalse(Sort sort);

    List<Realty> findByIsDeletedTrue(Sort sort);
}
