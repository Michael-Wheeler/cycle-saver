package com.cycle_saver.controller.ActivityVendor;

import com.cycle_saver.model.Activity;
import com.cycle_saver.model.StravaToken;
import com.cycle_saver.model.User;
import com.cycle_saver.utils.StravaAuthentication;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class StravaController implements ActivityVendor {

    public String createUser(String authCode) throws IOException {
        StravaAuthentication stravaAuthentication = new StravaAuthentication();
        StravaToken accessToken = stravaAuthentication.authenticateNewUser(authCode);

        System.out.println("Add user from token info");
        //TODO User user = accessToken.addUser();

        System.out.println("Extract user activities and add to DB");
        //TODO database.addActivities.extractActivities(user);

        return "1033587";
        //TODO return user.Id();
    }

    public List<Activity> getCommutes(User user) {
        List<Activity> routesResponse = extractActivities(user);

        return filterCommutes(routesResponse);
    }

    public List<Activity> extractActivities(User user) {
        String accessToken = "e2192a3cebe2872cc31c4df1b3515b1ad4148abf";

        String stravaId = "1118050";
        //TODO String stravaId = user.getStravaId();

        HttpClient httpclient = HttpClients.createDefault();
        URI uri = null;
        try {
            uri = new URIBuilder()
                    .setScheme("https")
                    .setHost("www.strava.com")
                    .setPath("/api/v3/athletes/" + stravaId + "/activities")
                    .setParameter("per_page", "30")
                    .setParameter("access_token", accessToken)
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpGet httpget = new HttpGet(uri);


        String output = null;
        try {
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            InputStream instream = entity.getContent();
            output = IOUtils.toString(instream, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parseActivitiesResponse(output);
    }

    public List<Activity> parseActivitiesResponse(String response) {
        return new Gson().fromJson(response, new TypeToken<ArrayList<Activity>>(){}.getType());
    }

    public List<Activity> filterCommutes(List<Activity> activities) {
        List<Activity> commutes = activities;
        commutes.removeIf(activity -> !activity.getCommute());

        //TODO Remove this:
        commutes.forEach(activity -> System.out.println("Is Commute? " + activity.getCommute() + "   Activity Name: " + activity.getName() + "\n"));

        return commutes;
    }
}


