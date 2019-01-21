package com.cycle_saver.model;

import com.mongodb.BasicDBObject;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class User extends Document{

    private ObjectId id;
    private Athlete athlete;
    private String token;
    private ArrayList<Journey> journeys;

    public User(Athlete athlete, String token) {
        this.athlete = athlete;
        this.token = token;
        this.journeys = new ArrayList<Journey>();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ArrayList<Journey> getJourneys() {
        return journeys;
    }

    public void addJourney(Journey journey) {
        this.journeys.add(journey);
    }

    public void setJourneys(ArrayList<Journey> journeys) {
        this.journeys = journeys;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id.toString() + '\'' +
                ", athlete=" + athlete.toString() +
                ", token='" + token + '\'' +
                ", journeys=" + journeys +
                '}';
    }

}
