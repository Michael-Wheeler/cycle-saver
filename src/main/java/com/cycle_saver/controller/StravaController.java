package com.cycle_saver.controller;

import com.cycle_saver.model.Activity;
import com.cycle_saver.model.Athlete;
import com.cycle_saver.model.StravaToken;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import com.google.gson.reflect.TypeToken;

import java.util.stream.Collectors;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class StravaController {

    public void getActivities(Athlete athlete, String accessToken) throws IOException {
        HttpClient httpclient = HttpClients.createDefault();
        URI uri = null;
        try {
            uri = new URIBuilder()
                    .setScheme("https")
                    .setHost("www.strava.com")
                    .setPath("/api/v3/athletes/" + athlete.getId() + "/activities")
                    .setParameter("per_page", "200")
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


