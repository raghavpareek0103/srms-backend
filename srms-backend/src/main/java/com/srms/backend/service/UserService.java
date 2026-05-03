package com.srms.backend.service;

import com.srms.backend.entity.User;

public interface UserService {
    User findByUsername(String username);
}