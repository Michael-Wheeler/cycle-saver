package com.cycle_saver.controller.Strava;

import com.cycle_saver.model.Strava.Activity;
import com.cycle_saver.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ActivityController {
    public List<Activity> filterCommutes(List<Activity> activities) {
        activities.removeIf(activity -> !activity.getCommute());
        return activities;
    }

    public LocalDateTime extractStartDateTime(Activity activity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        LocalDateTime startDateTime = LocalDateTime.parse(activity.getStartDateLocal(), formatter);
        return startDateTime;
    }
}


