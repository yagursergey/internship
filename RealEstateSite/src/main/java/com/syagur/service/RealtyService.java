package com.syagur.service;

import com.syagur.dto.RealtyDto;
import com.syagur.entity.Realty;
import com.syagur.entity.User;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface RealtyService {

    List<Realty> findAllNotDeletedAndSort(Sort sort);

    List<Realty> findByOwnerAndSort(User owner, Sort sort);

    void edit(RealtyDto realtyDto, Long id);

    List<Realty> getAllDeleted(Sort sort);

    void saveNewRealty(Realty realty);

    void undeleteById(Long id);

    Realty findById(Long id);

    void deleteById(Long id);
}
