package com.syagur.user;

import com.syagur.common.util.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController()
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    private final Converter converter;

    @GetMapping
    ResponseEntity<Page<UserDto>> getAllUsers(
            @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(value = "sortWith", defaultValue = "ASC") String sortWith) {

        Pageable pageable = createPageable(pageNum, sortBy, sortWith);

        Page<User> users = userService.findAll(pageable);
        Page<UserDto> userDtos = users.map(converter::toUserDto);

        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        UserDto userDto = converter.toUserDto(userService.findById(id));
        return ResponseEntity.ok(userDto);
    }

    private Pageable createPageable(Integer pageNum, String sortBy, String sortWith) {
        return PageRequest.of(pageNum, 10, Sort.by(Sort.Direction.valueOf(sortWith), sortBy));
    }
}
