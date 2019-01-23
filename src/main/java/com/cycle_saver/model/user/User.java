package com.cycle_saver.model.user;

import com.cycle_saver.model.strava.Athlete;

import java.util.List;

import org.bson.types.ObjectId;

public class User {

    private ObjectId id;
    private List<String> journeyIds;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Athlete athlete;
    private String profileUrl;
    private String stravaAccessToken;

    //TODO Remove this after testing
    public User(String stravaAccessToken) {
        this.stravaAccessToken = stravaAccessToken;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(Athlete athlete, String stravaAccessToken) {
        this.athlete = athlete;
        this.stravaAccessToken = stravaAccessToken;
    }

    public User() {}

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

    public String getStravaAccessToken() {
        return stravaAccessToken;
    }

    public void setStravaAccessToken(String stravaAccessToken) {
        this.stravaAccessToken = stravaAccessToken;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getId() { return id.toHexString(); }

    public void setId(ObjectId id) { this.id = id; }

    public List<String> getJourneyIds() { return journeyIds; }

    public void setJourneyIds(List<String> journeyIds) { this.journeyIds = journeyIds; }

    //public void setJourneyId(String journeyId) {this.journeyIds.add(journeyId); }
}
