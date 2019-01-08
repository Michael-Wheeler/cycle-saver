package com.cycle_saver.controller.Strava;

import com.cycle_saver.model.Strava.Activity;
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

public class StravaClient {
    public List<Activity> extractActivities(User user) {
        HttpClient httpclient = HttpClients.createDefault();
        URI uri = null;

        try {
            uri = new URIBuilder()
                    .setScheme("https")
                    .setHost("www.strava.com")
                    .setPath("/api/v3/athletes/" + user.getAthleteId() + "/activities")
                    .setParameter("access_token", user.getToken())
                    .setParameter("per_page", "30")
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        String output = "";
        try {
            HttpGet httpget = new HttpGet(uri);
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            InputStream inStream = entity.getContent();
            output = IOUtils.toString(inStream, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parseActivitiesResponse(output);
    }


    public List<Activity> parseActivitiesResponse(String response) {
        return new Gson().fromJson(response, new TypeToken<ArrayList<Activity>>() {
        }.getType());
    }
}
