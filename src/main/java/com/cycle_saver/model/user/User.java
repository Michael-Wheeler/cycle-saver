package com.cycle_saver.model.user;

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

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer stravaId;
    private String profileUrl;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStravaId() {
        return stravaId;
    }

    public void setStravaId(Integer stravaId) {
        this.stravaId = stravaId;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
