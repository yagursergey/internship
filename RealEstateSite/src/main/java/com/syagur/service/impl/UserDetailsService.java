package com.syagur.service.impl;

import com.syagur.entity.User;
import com.syagur.entity.enums.UserRole;
import com.syagur.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.USER);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        if (findUserByEmail(email).isPresent()) {
            User user = findUserByEmail(email).get();
            List<GrantedAuthority> authorities = getUserAuthority(email);
            return buildUserForAuthentication(user, authorities);
        } else {
            throw new UsernameNotFoundException("email not found");
        }
    }

    private List<GrantedAuthority> getUserAuthority(String email) {
        User user = this.findUserByEmail(email).get();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    public User getAuthenticatedUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return this.findUserByEmail(userEmail).get();
    }
}