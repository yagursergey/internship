package com.syagur.controller;

import com.syagur.dto.UserDto;
import com.syagur.service.impl.UserServiceImpl;
import com.syagur.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController()
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private Converter converter;

    @GetMapping
    ResponseEntity<List<UserDto>> getAllUsers(
            @RequestParam(value = "orderValue", defaultValue = "id") String orderValue,
            @RequestParam(value = "orderBy", defaultValue = "ASC") String orderBy) {

        Sort sort = Sort.by(Sort.Direction.valueOf(orderBy), orderValue);

        List<UserDto> userDtos = userService.findAll(sort)
                .stream()
                .map(converter::toUserDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        UserDto userDto = converter.toUserDto(userService.findById(id));
        return ResponseEntity.ok(userDto);
    }
}
