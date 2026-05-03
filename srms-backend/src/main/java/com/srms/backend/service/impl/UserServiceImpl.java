package com.srms.backend.service.impl;

import com.srms.backend.entity.User;
import com.srms.backend.exception.ResourceNotFoundException;
import com.srms.backend.repository.UserRepository;
import com.srms.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}