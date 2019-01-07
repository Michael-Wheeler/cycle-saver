package com.cycle_saver.model;

import java.util.ArrayList;

public class User {
    
    private int athleteId;
    private String token;
    private ArrayList<Journey> journeys;

    public User(int athleteId, String token) {
        this.athleteId = athleteId;
        this.token = token;
        this.journeys = new ArrayList<Journey>();
    }

    public int getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(int athleteId) {
        this.athleteId = athleteId;
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
}
