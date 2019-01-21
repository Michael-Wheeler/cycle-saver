/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cycle_saver;

import com.cycle_saver.controller.StravaAuthController;
import com.cycle_saver.controller.StravaController;
import com.cycle_saver.controller.TFLController;
import com.cycle_saver.model.Activity;
import com.cycle_saver.model.Athlete;
import com.cycle_saver.model.StravaToken;
import com.cycle_saver.model.User;
import com.cycle_saver.service.UserDataService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }

    @RequestMapping("/")
    String index() {
        return "index";
    }

    @RequestMapping("/auth/strava")
    String auth(@RequestParam(value = "state") String state,
                @RequestParam(value = "code") String code,
                @RequestParam(value = "scope") String scope) throws IOException {
        //get access token
        StravaAuthController stravaAuthController = new StravaAuthController();
        StravaToken token = stravaAuthController.getAccessToken(code);

        //add user to mongo
        User user = new User(token.getAthlete(), token.getAccessToken());
        UserDataService userDataService = new UserDataService();
        User userMongo = userDataService.addUser(user);
        System.out.println("MONGO USER " + userMongo.toString());
        Athlete athlete = token.getAthlete();
        StravaController stravaController = new StravaController();
        List<Activity> commuteActivities = stravaController.getCommutes(userMongo);

        TFLController tfl = new TFLController();
        athlete.getActivities().forEach(activity -> userMongo.addJourney(tfl.calculateJourney(
                activity,
                "421beaf3651ef6dcea93a05e0bb3dd86",
                "621b4307")));
        userDataService.updateUserJourneys(userMongo);
        return "auth_strava";
    }

}
