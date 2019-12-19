package com.syagur.realty;

import com.syagur.common.security.service.UserDetailsService;
import com.syagur.common.util.Converter;
import com.syagur.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@CrossOrigin
@RestController
@RequestMapping("/realties")
@RequiredArgsConstructor
public class RealtyController {

    private final RealtyService realtyService;

    private final Converter converter;

    private final UserDetailsService userDetailsService;

    @GetMapping
    ResponseEntity<Page<RealtyDto>> getAllRealties(
            @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(value = "sortWith", defaultValue = "ASC") String sortWith) {

        Pageable pageable = createPageable(pageNum, sortBy, sortWith);

        Page<Realty> realty = realtyService.findAllNotDeleted(pageable);
        Page<RealtyDto> page = realty.map(converter::toRealtyDto);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/my")
    ResponseEntity<Page<RealtyDto>> getRealtiesByUserId(
            @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(value = "sortWith", defaultValue = "ASC") String sortWith) {

        User owner = userDetailsService.getAuthenticatedUser();
        Pageable pageable = createPageable(pageNum, sortBy, sortWith);

        Page<Realty> realty = realtyService.findByOwner(owner, pageable);
        Page<RealtyDto> realtyDtos = realty.map(converter::toRealtyDto);

        return ResponseEntity.ok(realtyDtos);
    }

    @GetMapping("/deleted")
    ResponseEntity<Page<RealtyDto>> getAllDeletedRealties(
            @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(value = "sortWith", defaultValue = "ASC") String sortWith) {

        Pageable pageable = createPageable(pageNum, sortBy, sortWith);

        Page<Realty> realty = realtyService.getAllDeleted(pageable);
        Page<RealtyDto> realtyDtos = realty.map(converter::toRealtyDto);

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
        User owner = userDetailsService.getAuthenticatedUser();
        realtyService.isOwner(owner, id);

        Realty realty = converter.toRealty(realtyDto);
        realtyService.edit(realty, owner);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteRealty(@PathVariable("id") Long id) {
        User owner = userDetailsService.getAuthenticatedUser();
        realtyService.isOwner(owner, id);
        realtyService.deleteById(id);

        return ResponseEntity.status(204).build();
    }

    private Pageable createPageable(Integer pageNum, String sortBy, String sortWith) {
        return PageRequest.of(pageNum, 10, Sort.by(Sort.Direction.valueOf(sortWith), sortBy));
    }
}
