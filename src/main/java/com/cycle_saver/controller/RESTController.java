package com.cycle_saver.controller;

import com.cycle_saver.controller.ActivityVendor.StravaController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller

public class RESTController {
    @RequestMapping("/")
    String index() {
        return "index";
    }

    @RequestMapping("/auth/strava")
    String auth(@RequestParam(value = "state") String state,
                @RequestParam(value = "code") String authCode,
                @RequestParam(value = "scope") String scope) throws IOException {

        StravaController stravaController = new StravaController();
        String userId = stravaController.createUser(authCode);


//
//        User user = new User();
//        TFLController tfl = new TFLController();
//
//        System.out.println(athlete.getActivities().size());
////        athlete.getActivities()
////                .removeIf(activity -> activity.getStartLatlng() == activity.getEndLatlng());
//        System.out.println(athlete.getActivities().size());
//        athlete.getActivities()
//                .forEach(activity -> user.addJourney(tfl.calculateJourney(
//                        activity,
//                        "421beaf3651ef6dcea93a05e0bb3dd86",
//                        "621b4307"))
//                );
        return "dashboard";
    }
}
