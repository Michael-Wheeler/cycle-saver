package com.cycle_saver.controller.ActivityVendor;

import com.cycle_saver.model.Activity;
import com.cycle_saver.model.User;

import java.util.List;

public interface ActivityVendor {
    List<Activity> getCommutes(User user);
}
