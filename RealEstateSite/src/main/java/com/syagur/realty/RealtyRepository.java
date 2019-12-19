package com.syagur.realty;

import com.syagur.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RealtyRepository extends JpaRepository<Realty, Long> {

    Optional<Page<Realty>> findByOwnerAndIsDeletedFalse(User owner, Pageable pageable);

    Optional<Page<Realty>> findByIsDeletedFalse(Pageable pageable);

    Optional<Page<Realty>> findByIsDeletedTrue(Pageable pageable);
}
