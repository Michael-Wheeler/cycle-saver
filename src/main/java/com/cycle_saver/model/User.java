package com.cycle_saver.model;

import java.util.ArrayList;

public class User {

    private ArrayList<Journey> journeys;

    public User(ArrayList<Journey> journeys) {
        this.journeys = journeys;
    }

    public User() {
        this.journeys = new ArrayList<Journey>();
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
