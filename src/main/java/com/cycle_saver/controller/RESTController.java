package com.cycle_saver.controller;

import com.cycle_saver.controller.UserManagement.StravaUser;
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

        StravaUser stravaUser = new StravaUser();
        stravaUser.createUser(authCode);

        return "dashboard";
    }
}
