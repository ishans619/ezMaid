package com.ezmaid.service;

import java.util.List;
import java.util.Optional;

import com.ezmaid.entity.User;

public interface UserService {

    List<User> getUsers();
    Optional<User> getUserByUsername(String username);

    boolean hasUserWithUsername(String username);

    User validateAndGetUserByUsername(String username);
    
    User saveUser(User user);
    void deleteUser(User user);
}
