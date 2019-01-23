package com.cycle_saver.dataFetchers;

import com.cycle_saver.model.user.Journey;
import com.cycle_saver.model.user.User;
import com.cycle_saver.services.JourneyService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JourneysDataFetcher  implements DataFetcher<User> {
    private final JourneyService journeyService;

    @Autowired
    JourneysDataFetcher(JourneyService journeyService){
        this.journeyService = journeyService;
    }

    public List<Journey> getJourneys(DataFetchingEnvironment env){
        User user = env.getSource();
        List<String> journeyIds = new ArrayList<>();
        if(user != null){
            journeyIds = user.getJourneyIds();
        }
        List<Journey> journeys = journeyService.findAllUserJourneys(journeyIds);
        return journeys;
    }

    @Override
    public User get(DataFetchingEnvironment dataFetchingEnvironment) {
        return null;
    }
}
