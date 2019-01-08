package com.cycle_saver.model.Strava;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StravaToken {

    @SerializedName("token_type")
    @Expose
    private String tokenType;
    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("athlete")
    @Expose
    private Athlete athlete;

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    @Override
    public String toString() {
        return "StravaToken{" +
                "token_type='" + tokenType + '\'' +
                ", access_token='" + accessToken + '\'' +
                ", athlete='" + athlete.getFirstname() + '\'' +
                '}';
    }
}
