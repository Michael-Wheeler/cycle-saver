package com.cycle_saver.services;

import com.cycle_saver.model.user.User;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User findOneById(ObjectId id);
    List<User> findByIdIn(List<String>ids);
    List<User> saveAll(List<User> users);
    User insert(User user);
    void deleteUser(User user);
    void deleteAll();
}
