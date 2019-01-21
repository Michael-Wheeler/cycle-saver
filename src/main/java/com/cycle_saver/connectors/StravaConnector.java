package com.cycle_saver.connectors;

import com.cycle_saver.model.Strava.Activity;
import com.cycle_saver.model.Strava.StravaToken;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class StravaConnector {

    private HttpClient httpclient;

    public StravaConnector() {
        this.httpclient = HttpClients.createDefault();
    }

    public StravaToken requestAccessToken(String authCode) throws IOException {
        HttpPost httppost = new HttpPost("https://www.strava.com/oauth/token");
        List<NameValuePair> params = new ArrayList<NameValuePair>(3);
        //TODO Remove Strava secrets
        params.add(new BasicNameValuePair("client_id", "28817"));
        params.add(new BasicNameValuePair("client_secret", "f0949137a6f81f4748fedfe204b28c7d02bfb46e"));
        params.add(new BasicNameValuePair("code", authCode));
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        HttpResponse response = httpclient.execute(httppost);
        return parseAccessTokenResponse(IOUtils.toString(response.getEntity().getContent(), "UTF-8"));
    }

    public StravaToken parseAccessTokenResponse(String accessTokenResponse) {
        return new Gson().fromJson(accessTokenResponse, StravaToken.class);
    }

    public List<Activity> getActivities(int athleteId, String accessToken) throws URISyntaxException, IOException {
        URI uri = new URIBuilder()
                .setScheme("https")
                .setHost("www.strava.com")
                .setPath("/api/v3/athletes/" + athleteId + "/activities")
                .setParameter("access_token", accessToken)
                .setParameter("per_page", "200")
                .setParameter("nationalSearch", "true")
                .build();
        HttpGet httpget = new HttpGet(uri);

        HttpResponse response = httpclient.execute(httpget);
        return parseActivitiesResponse(IOUtils.toString(response.getEntity().getContent(), "UTF-8"));
    }

    public List<Activity> parseActivitiesResponse(String response) {
        return new Gson().fromJson(response, new TypeToken<ArrayList<Activity>>() {
        }.getType());
    }
}
