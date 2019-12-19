package com.syagur.realty;

import com.syagur.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RealtyService {

    Page<Realty> findAllNotDeleted(Pageable pageable);

    Page<Realty> findByOwner(User owner, Pageable pageable);

    void edit(Realty realty, User owner);

    Page<Realty> getAllDeleted(Pageable pageable);

    void saveNewRealty(Realty realty);

    void undeleteById(Long id);

    Realty findById(Long id);

    void deleteById(Long id);

    void isOwner(User owner, Long id);
}
