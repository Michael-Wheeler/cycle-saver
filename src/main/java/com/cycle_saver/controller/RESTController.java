package com.cycle_saver.controller;

import com.cycle_saver.controller.UserManagement.StravaUser;
import com.cycle_saver.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        User user = stravaUser.createUser(authCode);

        return "redirect:/dashboard?totalSavings=" + user.getTotalSavings();
    }

    @RequestMapping("/dashboard")
    String userDashboard(Model model, @RequestParam(value = "userId") String userId){
        model.addAttribute("totalSavings", db.getUser(id).getTotalSavings);
        return "userDashboard";
    }
}
