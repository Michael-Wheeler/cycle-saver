package com.cycle_saver.repositories;

import com.cycle_saver.model.user.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByIdIn(List<String> ids);
    List<User> findByStravaAccessToken(String stravaAccessToken);
    User findById(ObjectId id);
}