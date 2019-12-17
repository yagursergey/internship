package com.syagur.realty;

import com.syagur.common.util.Converter;
import com.syagur.common.security.service.UserDetailsService;
import com.syagur.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@CrossOrigin
@RestController
@RequestMapping("/realties")
@RequiredArgsConstructor
public class RealtyController {

    private final RealtyService realtyService;

    private final Converter converter;

    private final UserDetailsService userDetailsService;

//  todo ----  This is old endpoint without pagination. Should be removed after work will be done with new;
    @GetMapping()
    ResponseEntity<List<RealtyDto>> getAllRealties(
            @RequestParam(value = "orderValue", defaultValue = "id") String orderValue,
            @RequestParam(value = "orderBy", defaultValue = "ASC") String sortWith) {

        Sort sort = Sort.by(Sort.Direction.valueOf(sortWith), orderValue);

        List<Realty> realty = realtyService.findAllNotDeletedAndSort(sort);
        List<RealtyDto> realtyDtos = toRealtyDtosList(realty);

        return ResponseEntity.ok(realtyDtos);
    }

//    todo ---- This is new endpoint with pagination.
    @GetMapping
    ResponseEntity<Page<RealtyDto>> getAllRealties(
            @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(value = "sortWith", defaultValue = "ASC") String sortWith
    ) {
        Pageable pageable = createPageable(pageNum, sortBy, sortWith);

        Page<Realty> realty = realtyService.findAll(pageable);

        Page<RealtyDto> page = realty.map(converter::toRealtyDto);

        return ResponseEntity.ok(page);
    }

    private Pageable createPageable(Integer pageNum, String sortBy, String sortWith) {
        return  PageRequest.of(pageNum, 10, Sort.by(Sort.Direction.valueOf(sortWith), sortBy));
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
        return ResponseEntity.status(204).build();
    }

    @PatchMapping("/{id}")
    ResponseEntity<Void> patchReality(@PathVariable("id") Long id, @RequestBody RealtyDto realtyDto) {
        realtyService.edit(realtyDto, id);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteRealty(@PathVariable("id") Long id) {
        realtyService.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    private List<RealtyDto> toRealtyDtosList(List<Realty> realties) {
        return realties
                .stream()
                .map(converter::toRealtyDto)
                .collect(Collectors.toList());
    }
}
