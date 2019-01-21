package com.cycle_saver.controller.strava;

import com.cycle_saver.connectors.StravaConnector;
import com.cycle_saver.connectors.TFLConnector;
import com.cycle_saver.controller.BaseController;
import com.cycle_saver.model.Strava.Activity;
import com.cycle_saver.model.Strava.Athlete;
import com.cycle_saver.model.Strava.StravaAuth;
import com.cycle_saver.model.Strava.StravaToken;
import com.cycle_saver.model.user.User;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/strava")
public class StravaController extends BaseController {

    private static Logger logger = Logger.getLogger(StravaController.class.getName());

    private StravaConnector stravaConnector = new StravaConnector();

    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    @Produces(MediaType.APPLICATION_JSON)
    public Response connect(@RequestParam(value = "user") Integer user) throws IOException {
        //localhost:5000/auth/strava
        String url = "https://www.strava.com/oauth/authorize?client_id=28817&response_type=code" +
                "&redirect_uri=http://localhost:5000%2Fstrava%2Fauth&approval_prompt=force";

        if (user != null) {
            StravaState state = new StravaState();
            state.setUserId(user);
            url = url + "&state=" + Base64.getEncoder().encodeToString(new Gson().toJson(state).getBytes());
        }
        return Response.ok(url).build();
    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    @Produces(MediaType.APPLICATION_JSON)
    public Response auth(@RequestParam(value = "state") String state,
                         @RequestParam(value = "code") String code,
                         @RequestParam(value = "scope") String scope) throws IOException {

        try {
            User u = null;
            if (state != null) {
                StravaState stravaState = new Gson().fromJson(new String(Base64.getDecoder().decode(state)), StravaState.class);
                u = queryUser(stravaState.getUserId());
            }
            if (u == null) {
                // new user signed up from Strava!
                u = new User();
            }
            StravaAuth stravaAuth = new StravaAuth(state, code, scope);
            logger.info("Authorisation Information is: " + stravaAuth.toString());
            StravaToken token = stravaConnector.requestAccessToken(code);
            Athlete athlete = token.getAthlete();

            // update our record of user with strava info
            u.setAthlete(athlete);

            // update user with journeys
            List<Activity> activities = stravaConnector.getActivities(athlete.getId(), token.getAccessToken());
            filterCommutes(athlete);
            TFLConnector tflController = new TFLConnector();
            for (Activity activity : activities) {
                try {
                    u.addJourney(tflController.buildJourney(activity));
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }

            // return athlete info to display on FE
            return Response.status(302).location(new URI(webUrl)).build();
        } catch (URISyntaxException u) {
            logger.severe("Failed to set uri: " + u.getMessage());
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    private void filterCommutes(Athlete athlete) {
        athlete.getActivities().removeIf(activity -> !activity.getCommute());
        //TODO Remove
        athlete.getActivities().forEach(activity -> System.out.println("Is Commute? " + activity.getCommute() + "   Activity Name: " + activity.getName() + "\n"));
    }
}


