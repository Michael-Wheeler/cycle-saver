package com.cycle_saver.controller;

import com.cycle_saver.model.user.Journey;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/journeys")
public class TFLController extends BaseController {

    @RequestMapping(value = "/tfl", method = RequestMethod.GET)
    @Produces(MediaType.APPLICATION_JSON)
    public void calculateJourney(Journey journey, String app_key, String app_id) throws IOException {
        String start_latlng = journey.getStart_latlng().toString().replaceAll("[\\[\\] ]", "");
        String end_latlng = journey.getEnd_latlng().toString().replaceAll("[\\[\\] ]", "");

        HttpClient httpclient = HttpClients.createDefault();
        URI uri = null;
        try {
            uri = new URIBuilder()
                    .setScheme("https")
                    .setHost("api.tfl.gov.uk")
                    .setPath("/journey/journeyresults/" + start_latlng + "/to/" + end_latlng)
                    .setParameter("app_key", app_key)
                    .setParameter("app_id", app_id)
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
        System.out.println(output);
        //parseActivitiesResponse(athlete, output);
    }
}
