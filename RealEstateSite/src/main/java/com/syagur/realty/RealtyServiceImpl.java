package com.syagur.realty;

import com.syagur.common.exception.exceptions.NoRightsForActionException;
import com.syagur.common.exception.exceptions.ResourceNotFoundException;
import com.syagur.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RealtyServiceImpl implements RealtyService {

    private final RealtyRepository realtyRepository;

    @Value("${message.resourceNotFound.realty.list}")
    private String messageForList;
    @Value("${message.resourceNotFound.realty.one}")
    private String messageForOne;
    @Value("${message.noRightsForAction}")
    private String messageForAction;

    @Override
    public Realty findById(Long id) {
        return realtyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(messageForOne));
    }

    @Override
    public Page<Realty> getAllDeleted(Pageable pageable) {
        return realtyRepository.findByIsDeletedTrue(pageable)
                .orElseThrow(() -> new ResourceNotFoundException(messageForList));
    }

    @Override
    public Page<Realty> findByOwner(User owner, Pageable pageable) {
        return realtyRepository.findByOwnerAndIsDeletedFalse(owner, pageable)
                .orElseThrow(() -> new ResourceNotFoundException(messageForList));
    }

    @Override
    public Page<Realty> findAllNotDeleted(Pageable pageable) {
        return realtyRepository.findByIsDeletedFalse(pageable)
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
    public void edit(Realty realty, User owner) {
        realty.setOwner(owner);
        realtyRepository.saveAndFlush(realty);
    }

    @Override
    public void isOwner(User owner, Long id) {
        Long ownerId = realtyRepository
                .getOne(id)
                .getOwner()
                .getId();
        if (!ownerId.equals(owner.getId())) {
            throw new NoRightsForActionException(messageForAction);
        }
    }
}
