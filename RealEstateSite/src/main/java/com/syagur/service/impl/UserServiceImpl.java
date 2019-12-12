package com.syagur.service.impl;

import com.syagur.entity.User;
import com.syagur.exception.UserNotFoundException;
import com.syagur.repository.UserRepository;
import com.syagur.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll(Sort sort) {
        return new ArrayList<>(userRepository.findAll(sort));
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

}
