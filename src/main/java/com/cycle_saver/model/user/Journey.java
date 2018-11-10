package com.cycle_saver.model.user;

public class Journey {

    private int activity_id;
    private int totalCost;
    private int duration;

    public Journey(int activity_id, int totalCost, int duration) {
        this.activity_id = activity_id;
        this.totalCost = totalCost;
        this.duration = duration;
    }

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Journey{" +
                "activity_id=" + activity_id +
                ", totalCost=" + totalCost +
                ", duration=" + duration +
                '}';
    }
}
