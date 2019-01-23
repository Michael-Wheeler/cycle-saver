package com.cycle_saver.model.user;

import com.cycle_saver.model.strava.Activity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;

public class Journey {

    private ObjectId id;
    private String userId;
    private int totalCost;
    private Activity activity;
    private String startLatLng;
    private String endLatLng;
    private LocalDateTime startDateTime;

    public Journey(Activity activity, int totalCost) {
        this.activity = activity;
        this.totalCost = totalCost;
    }

    public Journey(String userId, int totalCost) {
        this.userId = userId;
        this.totalCost = totalCost;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getStartLatLng() {
        return startLatLng;
    }

    public void setStartLatLng(String startLatLng) {
        this.startLatLng = startLatLng;
    }

    public String getEndLatLng() {
        return endLatLng;
    }

    public void setEndLatLng(String endLatLng) {
        this.endLatLng = endLatLng;
    }

    public LocalDateTime getStartTime() {
        return startDateTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startDateTime = startTime;
    }

    public String getId() { return id.toHexString(); }

    public void setId(ObjectId id) { this.id = id; }

    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }
}
