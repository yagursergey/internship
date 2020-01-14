package com.syagur.resourceserver.realty;

import com.syagur.resourceserver.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RealtyService {

    private final RealtyRepository realtyRepository;

    @Value("${message.resourceNotFound.realty.list}")
    private String messageForList;

    public Page<Realty> findAll(Pageable pageable) {
        return realtyRepository.findByIsDeletedFalse(pageable)
                .orElseThrow(() -> new ResourceNotFoundException(this.messageForList));
    }

    public void save(Realty realty) {
        realty.setDeleted(false);
        realty.setDateOfCreation(LocalDate.now());
        realtyRepository.save(realty);
    }

}
