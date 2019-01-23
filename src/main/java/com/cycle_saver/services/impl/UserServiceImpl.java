package com.cycle_saver.services.impl;

import com.cycle_saver.model.user.User;
import com.cycle_saver.repositories.UserRepository;
import com.cycle_saver.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return (ArrayList) userRepository.findAll();
    }

    @Override
    public User findOneById(ObjectId id) { return userRepository.findById(id); }

    @Override
    public List<User> findByIdIn(List<String> ids) {
        return userRepository.findByIdIn(ids);
    }

    public List<User> findByStravaAccessToken(String stravaAccessToken) { return userRepository.findByStravaAccessToken(stravaAccessToken); }
}
