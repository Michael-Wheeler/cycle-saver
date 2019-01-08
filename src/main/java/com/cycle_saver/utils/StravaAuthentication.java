package com.cycle_saver.utils;

import com.cycle_saver.model.Strava.StravaToken;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class StravaAuthentication {

    public StravaToken getAccessToken(String authCode) {
        StravaToken stravaToken = null;
        try {
            stravaToken = requestAccessToken(authCode);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return stravaToken;
    }

    public StravaToken requestAccessToken(String authCode) throws IOException {
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("https://www.strava.com/oauth/token");

        List<NameValuePair> params = new ArrayList<NameValuePair>(3);
        params.add(new BasicNameValuePair("client_id", "28817"));
        params.add(new BasicNameValuePair("client_secret", "f0949137a6f81f4748fedfe204b28c7d02bfb46e"));
        params.add(new BasicNameValuePair("code", authCode));
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        InputStream inStream = entity.getContent();
        String accessTokenResponse = IOUtils.toString(inStream, "UTF-8");
        StravaToken stravaToken = parseAccessTokenResponse(accessTokenResponse);
        return stravaToken;
    }

    public StravaToken parseAccessTokenResponse(String accessTokenResponse) {
        return new Gson().fromJson(accessTokenResponse, StravaToken.class);
    }
}

