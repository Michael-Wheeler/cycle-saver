package com.cycle_saver.connectors;

import com.cycle_saver.model.Strava.Activity;
import com.cycle_saver.model.Strava.Athlete;
import com.cycle_saver.model.Strava.StravaToken;
import com.cycle_saver.model.user.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class StravaConnector {

    private HttpClient httpclient;

    public StravaConnector() {
        this.httpclient = HttpClients.createDefault();
    }

    public List<Activity> extractActivities(User user) {

        try {
            URI uri = new URIBuilder()
                    .setScheme("https")
                    .setHost("www.strava.com")
                    .setPath("/api/v3/athletes/" + user.getAthlete().getId() + "/activities")
                    .setParameter("access_token", user.getStravaAthleteToken())
                    .setParameter("per_page", "200")
                    .setParameter("nationalSearch", "true")
                    .build();
            HttpGet httpget = new HttpGet(uri);
            HttpResponse response = httpclient.execute(httpget);
            String output = EntityUtils.toString(response.getEntity());
            return parseActivitiesResponse(output);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Activity> parseActivitiesResponse(String response) {
        return new Gson().fromJson(response, new TypeToken<ArrayList<Activity>>() {
        }.getType());
    }

    public StravaToken requestAccessToken(String authCode) throws IOException {
        HttpPost httppost = new HttpPost("https://www.strava.com/oauth/token");

        List<NameValuePair> params = new ArrayList<NameValuePair>(3);
        params.add(new BasicNameValuePair("client_id", "28817"));
        params.add(new BasicNameValuePair("client_secret", "f0949137a6f81f4748fedfe204b28c7d02bfb46e"));
        params.add(new BasicNameValuePair("code", authCode));
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        InputStream instream = entity.getContent();
        String accessTokenResponse = IOUtils.toString(instream, "UTF-8");
        System.out.println("ACCESS TOKEN RESPONSE: " + accessTokenResponse);
        StravaToken token = parseAccessTokenResponse(accessTokenResponse);
        Athlete athlete = token.getAthlete();
        // store athlete info and add to user data
        // store token for later use
        StravaToken stravaToken = parseAccessTokenResponse(accessTokenResponse);
        return stravaToken;
    }

    public StravaToken parseAccessTokenResponse(String accessTokenResponse) {
        return new Gson().fromJson(accessTokenResponse, StravaToken.class);
    }
}
