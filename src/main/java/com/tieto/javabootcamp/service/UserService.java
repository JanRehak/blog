package com.tieto.javabootcamp.service;

import com.tieto.javabootcamp.exception.DatabaseException;
import com.tieto.javabootcamp.model.User;

import java.util.List;

public interface UserService {

    User createUser(User name);

    User getUser(String name);

    List<User> getUsersByName(String name);

    List<User> getAllUsers();

    User updateUser(Long id, User user);

    boolean removeUser(String name);
	void removeUser(Long id);

    void authenticateUser();

    boolean verifyUser(String name);

}
