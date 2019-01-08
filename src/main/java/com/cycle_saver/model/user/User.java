package com.cycle_saver.model.user;

import com.cycle_saver.model.Strava.Athlete;

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
    private Athlete athlete;
    private String profileUrl;
    private String stravaAthleteToken;

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

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    public String getStravaAthleteToken() {
        return stravaAthleteToken;
    }

    public void setStravaAthleteToken(String stravaAthleteToken) {
        this.stravaAthleteToken = stravaAthleteToken;
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
