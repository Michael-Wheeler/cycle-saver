package com.cycle_saver.controller;

import com.cycle_saver.model.Strava.Activity;
import com.cycle_saver.model.Journey;
import com.cycle_saver.utils.CoordinatesFormatter;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class TFLClient {
    public Journey calculateJourney(Activity activity) {
        String startCoordinates = CoordinatesFormatter.formatCoords(activity.getStartLatlng());
        String endCoordinates = CoordinatesFormatter.formatCoords(activity.getEndLatlng());

        HttpClient httpclient = HttpClients.createDefault();
        URI uri = null;
        //TODO Remove TFL key and Id
        try {
            uri = new URIBuilder()
                    .setScheme("https")
                    .setHost("api.tfl.gov.uk")
                    .setPath("/journey/journeyresults/" + startCoordinates + "/to/" + endCoordinates)
                    .setParameter("time", "0900")
                    .setParameter("nationalSearch", "true")
                    .setParameter("app_key", "421beaf3651ef6dcea93a05e0bb3dd86")
                    .setParameter("app_id", "621b4307")
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

        Journey journey = deserialezeJourneyResponse(output, activity);
        return journey;
    }

    private Journey deserialezeJourneyResponse(String response, Activity activity) {
        JSONObject jsonObject = new JSONObject(response);
        JSONArray journeys = jsonObject.getJSONArray("journeys");
        JSONObject journeyJSON = journeys.getJSONObject(0);
        int totalCost = journeyJSON.getJSONObject("fare").getInt("totalCost");
        return new Journey(activity, totalCost);
    }
}
