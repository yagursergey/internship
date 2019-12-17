package com.syagur.realty;

import com.syagur.exception.exceptions.ResourceNotFoundException;
import com.syagur.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RealtyServiceImpl implements RealtyService {

    private final RealtyRepository realtyRepository;

    @Value("${message.resourceNotFound.realty.list}")
    private String messageForList;
    @Value("${message.resourceNotFound.realty.one}")
    private String messageForOne;

    public Page<Realty> findAll(Pageable pageable) {
        return realtyRepository.findAll(pageable);
    }

    @Override
    public Realty findById(Long id) {
        return realtyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(messageForOne));
    }

    @Override
    public List<Realty> getAllDeleted(Sort sort) {
        return realtyRepository.findByIsDeletedTrue(sort)
                .orElseThrow(() -> new ResourceNotFoundException(messageForList));
    }

    @Override
    public List<Realty> findByOwnerAndSort(User owner, Sort sort) {
        return realtyRepository.findByOwnerAndIsDeletedFalse(owner, sort)
                .orElseThrow(() -> new ResourceNotFoundException(messageForList));
    }

    @Override
    public List<Realty> findAllNotDeletedAndSort(Sort sort) {
        return realtyRepository.findByIsDeletedFalse(sort)
                .orElseThrow(() -> new ResourceNotFoundException(messageForList));
    }

    @Override
    public void undeleteById(Long id) {
        Realty realty = realtyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(messageForOne));
        realty.setDeleted(false);
        realtyRepository.saveAndFlush(realty);
    }

    @Override
    public void deleteById(Long id) {
        Realty realty = realtyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(messageForOne));
        realty.setDeleted(true);
        realtyRepository.saveAndFlush(realty);
    }

    public void saveNewRealty(Realty realty) {
        realty.setDeleted(false);
        realty.setDateOfCreation(LocalDate.now());
        realtyRepository.saveAndFlush(realty);
    }

    @Override
    public void edit(RealtyDto realtyDto, Long id) {

        Realty oldRealty = this.findById(id);

        oldRealty.setDescription(realtyDto.getDescription());
        oldRealty.setType(RealtyType.valueOf(realtyDto.getType()));
        oldRealty.setSquare(realtyDto.getSquare());
        oldRealty.setPrice(realtyDto.getPrice());

        realtyRepository.saveAndFlush(oldRealty);
    }
}
