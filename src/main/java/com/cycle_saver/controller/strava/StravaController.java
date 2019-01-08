package com.cycle_saver.controller.strava;

import com.cycle_saver.connectors.StravaConnector;
import com.cycle_saver.controller.BaseController;
import com.cycle_saver.model.Strava.Activity;
import com.cycle_saver.model.Strava.Athlete;
import com.cycle_saver.model.Strava.StravaAuth;
import com.cycle_saver.model.Strava.StravaToken;
import com.cycle_saver.model.user.User;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Logger;

@RestController
@RequestMapping("/strava")
public class StravaController extends BaseController {

    private static Logger logger = Logger.getLogger(StravaController.class.getName());

    private StravaConnector stravaConnector = new StravaConnector();

    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    @Produces(MediaType.APPLICATION_JSON)
    public Response connect(@RequestParam(value = "user") Integer user) throws IOException {
        //localhost:5000%2Fauth%2Fstrava
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
            // return athlete info to display on FE

            getRoutes(athlete, "e2192a3cebe2872cc31c4df1b3515b1ad4148abf");
            System.out.println("FILTERING COMMUTES");
            filterCommutes(athlete);

            // update / insert updated/new user

//        TFLController tfl = new TFLController();
//        athlete.getActivities().forEach(activity -> user.addJourney(tfl.calculateJourney(
//                activity,
//                "421beaf3651ef6dcea93a05e0bb3dd86",
//                "621b4307")));


            return Response.status(302).location(new URI(webUrl)).build();
        } catch (URISyntaxException u) {
            logger.severe("Failed to set uri: " + u.getMessage());
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

    }

    @RequestMapping(value = "/routes", method = RequestMethod.GET)
    @Produces(MediaType.APPLICATION_JSON)
    public void getRoutes(Athlete athlete, String accessToken) throws IOException {
        HttpClient httpclient = HttpClients.createDefault();
        URI uri = null;
        try {
            uri = new URIBuilder()
                    .setScheme("https")
                    .setHost("www.strava.com")
                    .setPath("/api/v3/athletes/" + athlete.getId() + "/activities")
                    .setParameter("access_token", accessToken)
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpGet httpget = new HttpGet(uri);
        System.out.println(httpget.getURI());

        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        InputStream instream = entity.getContent();
        String output = IOUtils.toString(instream, "UTF-8");
        parseActivitiesResponse(athlete, output);
    }

    private void parseActivitiesResponse(Athlete athlete, String results) throws IOException {
        ArrayList<Activity> activities  = new Gson().fromJson(results, new TypeToken<ArrayList<Activity>>(){}.getType());;
        athlete.setActivities(activities);
        System.out.println(athlete.getActivities().toString());
        athlete.getActivities().forEach(activity -> System.out.println("Is Commute? " + activity.getCommute() + "   Activity Name: " + activity.getName() + "\n"));
    }

    private void filterCommutes(Athlete athlete) {
        athlete.getActivities().removeIf(activity -> !activity.getCommute());
        athlete.getActivities().forEach(activity -> System.out.println("Is Commute? " + activity.getCommute() + "   Activity Name: " + activity.getName() + "\n"));
    }
}


