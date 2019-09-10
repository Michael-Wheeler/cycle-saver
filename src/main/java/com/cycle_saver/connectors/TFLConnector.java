package com.cycle_saver.connectors;

import com.cycle_saver.model.Strava.Activity;
import com.cycle_saver.model.user.Journey;
import com.cycle_saver.utils.CoordinatesFormatter;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class TFLConnector {

    private HttpClient httpclient;

    public TFLConnector() {
        this.httpclient = HttpClients.createDefault();
    }

    public Journey buildJourney(Activity activity) throws IOException, URISyntaxException {
        String startCoordinates = CoordinatesFormatter.formatCoords(activity.getStartLatlng());
        String endCoordinates = CoordinatesFormatter.formatCoords(activity.getEndLatlng());
        return deserialezeTFLJourneyResponse(planTFLJourney(startCoordinates, endCoordinates), activity);
    }

    public String planTFLJourney(String startCoordinates, String endCoordinates) throws URISyntaxException, IOException {
        URI uri = new URIBuilder()
                .setScheme("https")
                .setHost("api.tfl.gov.uk")
                .setPath("/journey/journeyresults/" + startCoordinates + "/to/" + endCoordinates)
                .setParameter("time", "0900")
                .setParameter("nationalSearch", "true")
                //TODO Remove TFL key and Id
                .setParameter("app_key", "421beaf3651ef6dcea93a05e0bb3dd86")
                .setParameter("app_id", "621b4307")
                .build();
        HttpGet httpget = new HttpGet(uri);
        HttpResponse response = httpclient.execute(httpget);
        return IOUtils.toString(response.getEntity().getContent(), "UTF-8");
    }

    private Journey deserialezeTFLJourneyResponse(String response, Activity activity) {
        JSONObject journey = new JSONObject(response).getJSONArray("journeys").getJSONObject(0);
        int totalCost = journey.getJSONObject("fare").getInt("totalCost");
        return new Journey(activity, totalCost);
    }
}
