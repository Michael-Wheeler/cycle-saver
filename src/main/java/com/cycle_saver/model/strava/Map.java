package com.cycle_saver.model.strava;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Map {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("summary_polyline")
    @Expose
    private String summaryPolyline;
    @SerializedName("resource_state")
    @Expose
    private Integer resourceState;

    public Map() {
    }

    public Map(String id, String summaryPolyline, Integer resourceState) {
        super();
        this.id = id;
        this.summaryPolyline = summaryPolyline;
        this.resourceState = resourceState;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSummaryPolyline() {
        return summaryPolyline;
    }

    public void setSummaryPolyline(String summaryPolyline) {
        this.summaryPolyline = summaryPolyline;
    }

    public Integer getResourceState() {
        return resourceState;
    }

    public void setResourceState(Integer resourceState) {
        this.resourceState = resourceState;
    }
}