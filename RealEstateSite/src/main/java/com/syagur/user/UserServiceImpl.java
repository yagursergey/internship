package com.syagur.user;

import com.syagur.exception.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Value("${message.resourceNotFound.user.one}")
    private String messageForOne;

    @Override
    public List<User> findAll(Sort sort) {
        return userRepository.findAll(sort);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(messageForOne));
    }

}
