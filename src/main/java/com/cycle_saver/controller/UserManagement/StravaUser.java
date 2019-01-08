package com.cycle_saver.controller.UserManagement;

import com.cycle_saver.controller.TFLController;
import com.cycle_saver.model.Activity;
import com.cycle_saver.model.StravaToken;
import com.cycle_saver.model.User;
import com.cycle_saver.service.UserDataService;
import com.cycle_saver.utils.StravaAuthentication;
import com.cycle_saver.controller.ActivityVendor.StravaController;

import java.util.List;

public class StravaUser implements UserController {
    public void createUser(String authCode) {
        StravaAuthentication stravaAuthentication = new StravaAuthentication();
        StravaToken stravaToken = stravaAuthentication.getAccessToken(authCode);

        //TODO Create user in a different class and then update with Strava data
        User user = new User(stravaToken.getAthlete().getId(), stravaToken.getAccessToken());
        UserDataService userDataService = new UserDataService();
        userDataService.addUser(user);

        StravaController stravaController = new StravaController();
        List<Activity> commuteActivities = stravaController.getCommutes(user);

        //TODO Move activity to journey calculations elsewhere?
        TFLController tfl = new TFLController();
        commuteActivities.forEach(activity -> user.addJourney(tfl.calculateJourney(activity)));
    }
}
