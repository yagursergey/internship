package com.syagur.service.impl;

import com.syagur.dto.RealtyDto;
import com.syagur.entity.Realty;
import com.syagur.entity.User;
import com.syagur.entity.enums.RealtyType;
import com.syagur.exception.RealtyNotFoundException;
import com.syagur.repository.RealtyRepository;
import com.syagur.service.RealtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public
class RealtyServiceImpl implements RealtyService {

    @Autowired
    private RealtyRepository realtyRepository;

    @Override
    public Realty findById(Long id) {
        return realtyRepository.findById(id).orElseThrow(() -> new RealtyNotFoundException("Realty not found"));
    }

    @Override
    public List<Realty> getAllDeleted(Sort sort) {
        return realtyRepository.findByIsDeletedTrue(sort);
    }

    @Override
    public List<Realty> findByOwnerAndSort(User owner, Sort sort) {
        return realtyRepository.findByOwnerAndIsDeletedFalse(owner, sort).orElseThrow(() -> new RealtyNotFoundException("This User doesn't have list of realty"));
    }

    @Override
    public void undeleteById(Long id) {
        Realty realty = realtyRepository.getOne(id);
        realty.setDeleted(false);
        realtyRepository.saveAndFlush(realty);
    }

    @Override
    public List<Realty> findAllNotDeletedAndSort(String value, String sortWith) {

        Sort.Direction sortWithASCOrDESC = Sort.Direction.valueOf(sortWith);

        return realtyRepository.findByIsDeletedFalse(Sort.by(sortWithASCOrDESC, value));
    }

    public void saveNewRealty(Realty realty) {
        realty.setDeleted(false);
        realty.setDateOfCreation(LocalDate.now());
        realtyRepository.saveAndFlush(realty);
    }

    @Override
    public void deleteById(Long id) {
        Realty realty = realtyRepository.getOne(id);
        realty.setDeleted(true);
        realtyRepository.saveAndFlush(realty);
    }

    @Override
    public void edit(RealtyDto realtyDto, Long id) {
        Realty oldRealty = this.findById(id);

        if (realtyDto.getPrice() != null) {
            oldRealty.setPrice(realtyDto.getPrice());
        }

        if (realtyDto.getSquare() != null) {
            oldRealty.setSquare(realtyDto.getSquare());
        }

        if (realtyDto.getType() != null) {
            oldRealty.setType(RealtyType.valueOf(realtyDto.getType()));
        }

        if (realtyDto.getDescription() != null) {
            oldRealty.setDescription(realtyDto.getDescription());
        }

        realtyRepository.saveAndFlush(oldRealty);
    }
}
