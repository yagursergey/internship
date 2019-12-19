package com.syagur.common.security;

import com.syagur.common.exception.exceptions.ResourceNotFoundException;
import com.syagur.common.security.jwt.JwtTokenProvider;
import com.syagur.common.security.service.UserDetailsService;
import com.syagur.common.util.Converter;
import com.syagur.user.User;
import com.syagur.user.UserDto;
import com.syagur.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping()
public class AuthController {

    @Autowired
    Converter converter;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserDetailsService userDetailsService;


    @PostMapping("/login")
    public ResponseEntity<Map<Object, Object>> login(@Valid @RequestBody UserDto data) {
        try {
            User user = this.userDetailsService.findUserByEmail(data.getEmail())
                    .orElseThrow(() -> new ResourceNotFoundException("Invalid email"));

            String username = user.getEmail();

            Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(user.getRole());

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = jwtTokenProvider.createToken(username, userRoles);

            Map<Object, Object> model = new HashMap<>();
            model.put("role", user.getRole());
            model.put("userFullname", user.getFirstName() + " " + user.getSecondName());
            model.put("token", token);

            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email/password supplied");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Map<Object, Object>> register(@Valid @RequestBody UserDto userDto) {

        if (userDetailsService.findUserByEmail(userDto.getEmail()).isPresent()) {
            throw new BadCredentialsException("User with email: " + userDto.getEmail() + " already exists");
        }

        User user = converter.toUser(userDto);

        userDetailsService.save(user);

        Map<Object, Object> model = new HashMap<>();
        model.put("message", "User registered successfully");

        return ResponseEntity.ok(model);
    }
}
