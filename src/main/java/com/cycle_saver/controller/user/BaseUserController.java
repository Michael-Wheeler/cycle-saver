package com.cycle_saver.controller.user;

import com.cycle_saver.controller.TFLClient;
import com.cycle_saver.model.user.User;
import com.cycle_saver.service.UserDataService;

public class BaseUserController implements UserController {
    @Override
    public void createUser() {
        User user = new User();
        UserDataService userDataService = new UserDataService();
        userDataService.addUser(user);
    }

    public void calculateTotalSavings(User user) {
//        user.getJourneys().forEach(journey -> user.setTotalSavings(user.getTotalSavings() + journey.getTotalCost()));
    }

    public void activityConverter(User user) {
        TFLClient tfl = new TFLClient();
//        user.getJourneys().forEach(journey -> user.addJourney(tfl.calculateJourney(journey.getActivity())));

        //TODO Correct?
        calculateTotalSavings(user);
    }
}
