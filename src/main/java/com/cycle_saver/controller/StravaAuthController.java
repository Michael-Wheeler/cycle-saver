package com.cycle_saver.controller;

import com.cycle_saver.model.strava.Athlete;
import com.cycle_saver.model.strava.StravaAuth;
import com.cycle_saver.model.strava.StravaToken;
import com.cycle_saver.model.user.User;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/strava/auth")
public class StravaAuthController extends BaseController {

    private static Logger logger = Logger.getLogger(StravaAuthController.class.getName());

    private StravaToken requestAccessToken(StravaAuth authResponse) throws IOException {
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
        System.out.println("ACCESS TOKEN RESPONSE: " + accessTokenResponse);
        StravaToken token = parseAccessTokenResponse(accessTokenResponse);
        Athlete athlete = token.getAthlete();
        // store athlete info and add to user data
        // store token for later use
        return token;
    }

    private StravaToken parseAccessTokenResponse(String accessTokenResponse) throws JsonSyntaxException {
        return new Gson().fromJson(accessTokenResponse, StravaToken.class);
    }

    @RequestMapping(value = "/connect", method = RequestMethod.GET)
    @Produces(MediaType.APPLICATION_JSON)
    Response auth(@RequestHeader("Authorization") String bearer,
                  @RequestParam(value = "state") String state,
                  @RequestParam(value = "code") String code,
                  @RequestParam(value = "scope") String scope) throws IOException {
        User u = checkAuth(bearer);
        StravaAuth stravaAuth = new StravaAuth(state, code, scope);
        logger.info("Authorisation Information is: " + stravaAuth.toString());
        StravaAuthController stravaAuthController = new StravaAuthController();
        StravaToken token = stravaAuthController.requestAccessToken(stravaAuth);
        Athlete athlete = token.getAthlete();
        // update our record of user with strava info
        u.setStravaId(athlete.getId());
        // return athlete info to display on FE
        return Response.ok(athlete).build();
    }
}

