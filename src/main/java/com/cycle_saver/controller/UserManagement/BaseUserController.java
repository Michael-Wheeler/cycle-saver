package com.cycle_saver.controller.UserManagement;

import com.cycle_saver.model.User;
import com.cycle_saver.service.UserDataService;

public class BaseUserController implements UserController{
    @Override
    public void createUser(String killMe) {
        User user = new User();
        UserDataService userDataService = new UserDataService();
        userDataService.addUser(user);
    }

    public void calculateTotalSavings(User user) {
        user.getJourneys().forEach(journey -> user.setTotalSavings(user.getTotalSavings() + journey.getTotalCost()));
    }
}
