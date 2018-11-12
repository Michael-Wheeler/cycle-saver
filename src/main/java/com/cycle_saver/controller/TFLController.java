package com.cycle_saver.controller;

import com.cycle_saver.model.Activity;
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

public class TFLController {

    public Journey calculateJourney(Activity activity, String app_key, String app_id) {
        CoordinatesFormatter coordinatesFormatter = new CoordinatesFormatter();
        String startCoordinates = coordinatesFormatter.main(activity.getStartLatlng());
        String endCoordinates = coordinatesFormatter.main(activity.getEndLatlng());
        System.out.println(activity.getStartDate());
        HttpClient httpclient = HttpClients.createDefault();
        URI uri = null;
        try {
            uri = new URIBuilder()
                    .setScheme("https")
                    .setHost("api.tfl.gov.uk")
                    .setPath("/journey/journeyresults/" + startCoordinates + "/to/" + endCoordinates)
                    .setParameter("app_key", app_key)
                    .setParameter("app_id", app_id)
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpGet httpget = new HttpGet(uri);
        System.out.println(httpget.getURI());
        String output = "";
        try {
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            InputStream instream = entity.getContent();
             output = IOUtils.toString(instream, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(output);
        Journey journey = deserialezeJourneyResponse(output, activity.getId());
        System.out.println(journey);
        return journey;
    }

    private Journey deserialezeJourneyResponse(String response, int activity_id) {
        JSONObject jsonObject = new JSONObject(response);
        JSONArray journeys = jsonObject.getJSONArray("journeys");
        JSONObject journeyJSON = journeys.getJSONObject(0);
        int duration = journeyJSON.getInt("duration");
        int totalCost = journeyJSON.getJSONObject("fare").getInt("totalCost");
        return new Journey(activity_id, totalCost, duration);
    }
}
