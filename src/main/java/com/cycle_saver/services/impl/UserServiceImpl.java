package com.cycle_saver.services.impl;

import com.cycle_saver.model.user.User;
import com.cycle_saver.repositories.UserRepository;
import com.cycle_saver.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

//    UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findOneById(ObjectId id) { return userRepository.findById(id); }

    @Override
    public List<User> findByIdIn(List<String> ids) {
        return userRepository.findByIdIn(ids);
    }

    @Override
    public List<User> saveAll(List<User> users){
        return userRepository.saveAll(users);
    }

    @Override
    public User insert(User user){
        return userRepository.insert(user);
    }

    @Override
    public void deleteUser(User user){
        userRepository.delete(user);
    }

    @Override
    public void deleteAll(){
        userRepository.deleteAll();
    }

    //public List<User> findByStravaAccessToken(String stravaAccessToken) { return userRepository.findByStravaAccessToken(stravaAccessToken); }
}
