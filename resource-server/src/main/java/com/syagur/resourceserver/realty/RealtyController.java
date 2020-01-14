package com.syagur.resourceserver.realty;

import com.syagur.resourceserver.common.util.Converter;
import lombok.RequiredArgsConstructor;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/realties")
@RequiredArgsConstructor
public class RealtyController {

    private final RealtyService realtyService;
    private final Converter converter;
    private final HttpServletRequest request;

    @GetMapping
    public ResponseEntity<Page<RealtyDto>> getAll(
            @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(value = "sortWith", defaultValue = "ASC") String sortWith
    ) {

        Pageable pageable = getPageable(pageNum, sortBy, sortWith);

        Page<Realty> realties = realtyService.findAll(pageable);
        Page<RealtyDto> realtyDtos = realties.map(converter::toRealtyDto);
        return ResponseEntity.ok(realtyDtos);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody RealtyDto realtyDto) {

        Realty realty = converter.toRealty(realtyDto);
        realty.setOwnerEmail(getAuthorizedUserEmail());

        realtyService.save(realty);

        URI location = fromCurrentRequest().buildAndExpand(realty.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    private Pageable getPageable(Integer pageNum, String sortBy, String sortWith) {
        return PageRequest.of(pageNum, 10, Sort.by(Sort.Direction.valueOf(sortWith), sortBy));
    }

    private String getAuthorizedUserEmail() {
        KeycloakSecurityContext context = (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
        return context.getToken().getEmail();
    }

}
