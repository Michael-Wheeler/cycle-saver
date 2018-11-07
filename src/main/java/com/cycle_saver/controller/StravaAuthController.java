package com.cycle_saver.controller;

import com.cycle_saver.model.StravaAuth;
import com.cycle_saver.model.StravaToken;
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

public class StravaAuthController {

    public StravaToken requestAccessToken(StravaAuth authResponse) throws IOException {
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("https://www.strava.com/oauth/token");

        List<NameValuePair> params = new ArrayList<NameValuePair>(3);
        params.add(new BasicNameValuePair("client_id", "28817"));
        params.add(new BasicNameValuePair("client_secret", "f0949137a6f81f4748fedfe204b28c7d02bfb46e"));
        params.add(new BasicNameValuePair("code", authResponse.getCode()));
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        InputStream instream = entity.getContent();
        String accessTokenResponse = IOUtils.toString(instream, "UTF-8");
        System.out.println("ACCESS TOKEN RESPONSE " + accessTokenResponse);
        StravaToken token = parseAccessTokenResponse(accessTokenResponse);
        return token;
    }

    public StravaToken parseAccessTokenResponse(String accessTokenResponse) throws IOException {
        return new Gson().fromJson(accessTokenResponse, StravaToken.class);
    }
}

