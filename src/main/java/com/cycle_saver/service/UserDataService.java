package com.cycle_saver.service;

import com.cycle_saver.model.Journey;
import com.cycle_saver.model.User;
import com.cycle_saver.db.MongoDbConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertOneOptions;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class UserDataService {

    private MongoCollection<User> getUserCollection(){
        MongoDatabase mongoDb = new MongoDbConnection().getDb();
        return mongoDb.getCollection("Users", User.class);
    }

    public User getUser(ObjectId id){
        MongoCollection<User> userCollection = getUserCollection();
        return userCollection.find(eq("_id", id.toString())).first();
    }

    public User addUser(User user){
        ObjectId id = new ObjectId();
        user.setId(id);
        System.out.println("ADD USER " + user.toString());
        System.out.println("ID STRING " + id.toString());
        getUserCollection().insertOne(user);
        return getUser(id);
    }

    public UpdateResult updateUserJourneys(User user){
        return getUserCollection().updateOne(eq("id", user.getId()), new Document("$set", new Document("journeys", user.getJourneys())));
    }

    public UpdateResult updateUser(String userId, String field, String value){
        return getUserCollection().updateOne(eq("id", userId), new Document("$set", new Document(field, value)));
    }
}
