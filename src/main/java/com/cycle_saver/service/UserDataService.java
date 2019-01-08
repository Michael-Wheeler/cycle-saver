package com.cycle_saver.service;

import com.cycle_saver.model.User;
import com.cycle_saver.db.MongoDbConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class UserDataService {

    private MongoCollection<User> getUserCollection(){
        MongoDatabase mongoDb = new MongoDbConnection().getDb();
        return mongoDb.getCollection("Users", User.class);
    }

    public User getUser(Integer userId){
        MongoCollection<User> userCollection = getUserCollection();
        return userCollection.find(eq("userId", userId)).first();
    }

    public void addUser(User user){
        getUserCollection().insertOne(user);
    }

    public UpdateResult updateUser(String userId, String field, String value){
        return getUserCollection().updateOne(eq("userId", userId), new Document("$set", new Document(field, value)));
    }
}
