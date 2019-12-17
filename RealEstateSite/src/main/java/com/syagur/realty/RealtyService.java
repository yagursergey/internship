package com.syagur.realty;

import com.syagur.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface RealtyService {

    Page<Realty> findAll(Pageable pageable);

    List<Realty> findAllNotDeletedAndSort(Sort sort);

    List<Realty> findByOwnerAndSort(User owner, Sort sort);

    void edit(RealtyDto realtyDto, Long id);

    List<Realty> getAllDeleted(Sort sort);

    void saveNewRealty(Realty realty);

    void undeleteById(Long id);

    Realty findById(Long id);

    void deleteById(Long id);
}
