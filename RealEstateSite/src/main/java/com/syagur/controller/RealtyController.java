package com.syagur.controller;

import com.syagur.dto.RealtyDto;
import com.syagur.entity.Realty;
import com.syagur.entity.User;
import com.syagur.service.RealtyService;
import com.syagur.service.impl.UserDetailsService;
import com.syagur.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@CrossOrigin
@RestController
@RequestMapping("/realties")
public class RealtyController {

    private final RealtyService realtyService;

    @Autowired
    private Converter converter;

    @Autowired
    private UserDetailsService userDetailsService;

    public RealtyController(RealtyService realtyService) {
        this.realtyService = realtyService;
    }

    @GetMapping()
    ResponseEntity<List<RealtyDto>> getAllRealties(
            @RequestParam(value = "orderValue", defaultValue = "id") String orderValue,
            @RequestParam(value = "orderBy", defaultValue = "ASC") String sortWith) {

        Sort sort = Sort.by(Sort.Direction.valueOf(sortWith), orderValue);

        List<Realty> realty = realtyService.findAllNotDeletedAndSort(sort);
        List<RealtyDto> realtyDtos = toRealtyDtosList(realty);

        return ResponseEntity.ok(realtyDtos);
    }

    @GetMapping("/my")
    ResponseEntity<List<RealtyDto>> getRealtiesByUserId(
            @RequestParam(value = "orderValue", defaultValue = "id") String orderValue,
            @RequestParam(value = "orderBy", defaultValue = "ASC") String orderBy) {

        User owner = userDetailsService.getAuthenticatedUser();
        Sort sort = Sort.by(Sort.Direction.valueOf(orderBy), orderValue);

        List<Realty> realty = realtyService.findByOwnerAndSort(owner, sort);
        List<RealtyDto> realtyDtos = toRealtyDtosList(realty);

        return ResponseEntity.ok(realtyDtos);
    }

    @GetMapping("/deleted")
    ResponseEntity<List<RealtyDto>> getAllDeletedRealties(
            @RequestParam(value = "orderValue", defaultValue = "id") String orderValue,
            @RequestParam(value = "orderBy", defaultValue = "ASC") String orderBy) {

        Sort sort = Sort.by(Sort.Direction.valueOf(orderBy), orderValue);

        List<Realty> realty = realtyService.getAllDeleted(sort);
        List<RealtyDto> realtyDtos = toRealtyDtosList(realty);

        return ResponseEntity.ok(realtyDtos);
    }

    @PostMapping
    ResponseEntity<Void> createNewRealty(@RequestBody RealtyDto realtyDto) {

        Realty realty = converter.toRealty(realtyDto);
        realty.setOwner(userDetailsService.getAuthenticatedUser());
        realtyService.saveNewRealty(realty);

        URI location = fromCurrentRequest().buildAndExpand(realty.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    ResponseEntity<RealtyDto> getRealtyById(@PathVariable("id") Long id) {
        RealtyDto realtyDto = converter.toRealtyDto(realtyService.findById(id));
        return ResponseEntity.ok(realtyDto);
    }

    @PatchMapping("/deleted/{id}")
    ResponseEntity<Void> undeleteCurrentRealty(@PathVariable("id") Long id) {
        realtyService.undeleteById(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    ResponseEntity<Void> patchReality(@PathVariable("id") Long id, @RequestBody RealtyDto realtyDto) {
        realtyService.edit(realtyDto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteRealty(@PathVariable("id") Long id) {
        realtyService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private List<RealtyDto> toRealtyDtosList(List<Realty> realties) {
        return realties
                .stream()
                .map(converter::toRealtyDto)
                .collect(Collectors.toList());
    }
}
