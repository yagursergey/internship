package com.syagur.service;

import com.syagur.entity.User;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface UserService {

    List<User> findAll(Sort sort);

    User findById(Long id);
}
