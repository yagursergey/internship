package com.syagur.resourceserver.realty;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RealtyRepository extends MongoRepository<Realty, Long> {

    Optional<Page<Realty>> findByIsDeletedFalse(Pageable pageable);

}
