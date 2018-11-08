package com.cycle_saver.model.user;

import java.lang.reflect.Array;
import java.util.List;

public class Journey {
    private List start_latlng;
    private List end_latlng;

    public Journey(List start_latlng, List end_latlng) {
        this.start_latlng = start_latlng;
        this.end_latlng = end_latlng;
    }

    public List getStart_latlng() {
        return start_latlng;
    }

    public void setStart_latlng(List start_latlng) {
        this.start_latlng = start_latlng;
    }

    public List getEnd_latlng() {
        return end_latlng;
    }

    public void setEnd_latlng(List end_latlng) {
        this.end_latlng = end_latlng;
    }


    @Override
    public String toString() {
        return "Journey{" +
                "start_latlng=" + start_latlng +
                ", end_latlng=" + end_latlng +
                '}';
    }
}
