package com.cycle_saver.service;

import com.cycle_saver.db.MongoDbConnection;
import com.cycle_saver.model.Athlete;
import com.cycle_saver.model.User;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

public class UserDataServiceTest {

    UserDataService userDataService;
    User user;
    MongoDatabase mongoDb;

    @Test
    public void addUserTest(){
        user = new User(new Athlete(), "12345");
        userDataService = new UserDataService();
        userDataService.addUser(user);
        mongoDb = new MongoDbConnection().getDb();
        MongoCollection<User> userCollection = mongoDb.getCollection("Users", User.class);
        User document = userCollection.find().first();
        System.out.println(document.toJson());
    }

    @Test
    public void getUserTest(){
        user = new User(new Athlete(), "12345");
        userDataService.addUser(user);
    }

}
