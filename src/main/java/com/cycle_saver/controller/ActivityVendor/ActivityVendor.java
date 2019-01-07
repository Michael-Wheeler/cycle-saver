package com.cycle_saver.controller.ActivityVendor;

import com.cycle_saver.model.Activity;
import com.cycle_saver.model.User;

import java.io.IOException;
import java.util.ArrayList;

public interface ActivityVendor {
    public ArrayList<Activity> extractActivities(User user) throws IOException;
}
