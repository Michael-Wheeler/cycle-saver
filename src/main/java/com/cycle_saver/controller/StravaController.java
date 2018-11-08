package com.cycle_saver.controller;

import com.cycle_saver.model.Activity;
import com.cycle_saver.model.strava.Athlete;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

@RestController
@RequestMapping("/strava")
public class StravaController extends BaseController {

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

    public void parseActivitiesResponse(Athlete athlete, String results) throws IOException {
        ArrayList<Activity> activities  = new Gson().fromJson(results, new TypeToken<ArrayList<Activity>>(){}.getType());;
        athlete.setActivities(activities);
        System.out.println(athlete.getActivities().toString());
        athlete.getActivities().forEach(activity -> System.out.println("Is Commute? " + activity.getCommute() + "   Activity Name: " + activity.getName() + "\n"));
    }

    public void filterCommutes(Athlete athlete) {
        athlete.getActivities().removeIf(activity -> !activity.getCommute());
        athlete.getActivities().forEach(activity -> System.out.println("Is Commute? " + activity.getCommute() + "   Activity Name: " + activity.getName() + "\n"));
    }
}


