package com.prasanna.OnlineExaminationSystem.service;

import com.prasanna.OnlineExaminationSystem.Entity.User;

public interface UserService {

    User saveUser(User user);
    User findByUsername(String username);
}
