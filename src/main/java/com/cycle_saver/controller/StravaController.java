package com.cycle_saver.controller;

import com.cycle_saver.model.Activity;
import com.cycle_saver.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class StravaController {

    public List<Activity> getCommutes(User user) {
        String routesResponse = null;
        try {
            routesResponse = getRoutes(user);

        } catch (IOException e) {
            System.out.println("Problem getting routes " + e.getMessage().toString());
        }
        return filterCommutes(parseActivitiesResponse(routesResponse));
    }

    public String getRoutes(User user) throws IOException {
        HttpClient httpclient = HttpClients.createDefault();
        URI uri = null;
        try {
            uri = new URIBuilder()
                    .setScheme("https")
                    .setHost("www.strava.com")
                    .setPath("/api/v3/athletes/" + user.getAthleteId() + "/activities")
                    .setParameter("access_token", user.getToken())
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpGet httpget = new HttpGet(uri);

        HttpResponse response = null;
        String output;
        response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        InputStream instream = entity.getContent();
        output = IOUtils.toString(instream, "UTF-8");
        return output;
    }

    public List<Activity> parseActivitiesResponse(String results) {
        return new Gson().fromJson(results, new TypeToken<ArrayList<Activity>>() {
        }.getType());
    }

    public List<Activity> filterCommutes(List<Activity> activities) {
        activities.removeIf(activity -> !activity.getCommute());
        activities.forEach(activity -> System.out.println("Is Commute? " + activity.getCommute() + "   Activity Name: " + activity.getName() + "\n"));
        return activities;
    }
}


