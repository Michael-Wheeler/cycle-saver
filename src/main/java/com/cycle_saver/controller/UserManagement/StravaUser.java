package com.cycle_saver.controller.UserManagement;

import com.cycle_saver.controller.Strava.StravaClient;
import com.cycle_saver.model.Strava.Activity;
import com.cycle_saver.controller.Strava.ActivityController.*;
import com.cycle_saver.model.Strava.StravaToken;
import com.cycle_saver.model.User;
import com.cycle_saver.utils.StravaAuthentication;
import com.cycle_saver.controller.Strava.ActivityController;

import java.util.List;

public class StravaUser extends BaseUserController {
    public void connectStravaAccount(String authCode) {
        StravaAuthentication stravaAuthentication = new StravaAuthentication();
        StravaToken stravaToken = stravaAuthentication.getAccessToken(authCode);

        User user = new User(stravaToken.getAthlete().getId(), stravaToken.getAccessToken());

        List<Activity> commuteActivities = getCommutes(user);
    }

    public List<Activity> getCommutes(User user) {
        ActivityController activityController = new ActivityController();
        StravaClient stravaClient = new StravaClient();

        List<Activity> activities = stravaClient.extractActivities(user);
        return activityController.filterCommutes(activities);
    }
}
