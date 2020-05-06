package com.tieto.javabootcamp.dao;

import com.tieto.javabootcamp.exception.DatabaseException;
import com.tieto.javabootcamp.model.User;
import com.tieto.javabootcamp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class DefaultUserDao implements UserDao {

    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(User user) throws DatabaseException {

        User userSaved;
        try {
            userSaved = userRepository.save(user);
        } catch (Exception e) {
            throw new DatabaseException("Cannot save user.",e);
        }
        return userSaved;

    }

    @Override
    public Long deleteUser(String name) throws DatabaseException {
        return userRepository.deleteByName(name).orElseThrow(DatabaseException::new);
    }

    @Override
    public User getUser(String name) throws DatabaseException {
        return userRepository.findByName(name).orElseThrow(DatabaseException::new);
    }

    @Override
    public User getUserById(Long id) throws DatabaseException {
        return userRepository.findById(id).orElseThrow(DatabaseException::new);
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userRepository.findAllByName(name).orElseGet(ArrayList::new);
    }

    @Override
    public List<User> getAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
}
