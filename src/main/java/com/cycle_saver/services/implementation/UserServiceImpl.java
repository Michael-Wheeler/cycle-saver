package com.cycle_saver.services.implementation;

import com.cycle_saver.model.user.User;
import com.cycle_saver.repositories.UserRepository;
import com.cycle_saver.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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
    public User findOneById(ObjectId id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findByIdIn(List<String> ids) {
        return userRepository.findByIdIn(ids);
    }

    //    private MongoCollection<User> getUserCollection(){
//        MongoDatabase mongoDb = new MongoDbConnection().getDb();
//        return mongoDb.getCollection("Users", User.class);
//    }
//
//    public User getUser(Integer userId){
//        MongoCollection<User> userCollection = getUserCollection();
//        return userCollection.find(eq("userId", userId)).first();
//    }
//
//    public void addUser(User user){
//        getUserCollection().insertOne(user);
//    }
//
//    public UpdateResult updateUser(String userId, String field, String value){
//        return getUserCollection().updateOne(eq("userId", userId), new Document("$set", new Document(field, value)));
//    }
}
