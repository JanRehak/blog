package com.tieto.javabootcamp.service.impl;

import com.tieto.javabootcamp.MyWebSecurityConfigurerAdapter;
import com.tieto.javabootcamp.dao.UserDao;
import com.tieto.javabootcamp.exception.DatabaseException;
import com.tieto.javabootcamp.exception.NotFoundException;
import com.tieto.javabootcamp.model.User;
import com.tieto.javabootcamp.repository.UserRepository;

import com.tieto.javabootcamp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MyWebSecurityConfigurerAdapter myWebSecurityConfigurerAdapter;

    @Override
    public User createUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userDao.saveUser(user);
        } catch (DatabaseException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getUser(String name) {
        User user = null;
        try {
            user = userDao.getUser(name);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean removeUser(String name) {
        boolean isRemoved = false;
        try {
            userDao.deleteUser(name);
            isRemoved = true;
        } catch (DatabaseException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return isRemoved;
    }




    @Override
    public void removeUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);

        } else {
            throw new NotFoundException("User with supplied id does not exist. Also, you can only remove users as ADMIN");
        }
    }

    public User updateUser(Long id, User user) {
        User userToUpdate = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));

        if (!user.getName().isEmpty()) {
            userToUpdate.setName(user.getName());
        }
        if (!user.getPassword().isEmpty()) {
            userToUpdate.setPassword(user.getPassword());
        }
//        userToUpdate.setRoles(user.getRoles());
        return userRepository.save(userToUpdate);
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userDao.getUsersByName(name);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void authenticateUser() {
        // Not implemented yet
    }


    public boolean verifyUser(String name) {
        if (userRepository.findByName(name).isPresent()) {
            return true;
        } else
            return false;
    }

}
