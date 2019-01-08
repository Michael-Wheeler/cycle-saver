package com.cycle_saver.model.user;

import com.cycle_saver.model.Strava.Activity;

import java.time.LocalDateTime;

public class Journey {

    private int totalCost;
    private Activity activity;
    private String start_latlng;
    private String end_latlng;
    private LocalDateTime startDateTime;

    public Journey(Activity activity, int totalCost) {
        this.activity = activity;
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

    public String getStart_latlng() {
        return start_latlng;
    }

    public void setStart_latlng(String start_latlng) {
        this.start_latlng = start_latlng;
    }

    public String getEnd_latlng() {
        return end_latlng;
    }

    public void setEnd_latlng(String end_latlng) {
        this.end_latlng = end_latlng;
    }

    public LocalDateTime getStartTime() {
        return startDateTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startDateTime = startTime;
    }
}
