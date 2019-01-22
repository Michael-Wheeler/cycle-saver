package com.cycle_saver.dataLoader;

import com.cycle_saver.model.user.Journey;
import com.cycle_saver.model.user.User;
import com.cycle_saver.repositories.JourneyRepository;
import com.cycle_saver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class MyDataLoader {
    private final UserRepository userRepository;
    private final JourneyRepository journeyRepository;

    @Autowired
    MyDataLoader(UserRepository userRepository, JourneyRepository journeyRepository) {
        this.userRepository = userRepository;
        this.journeyRepository = journeyRepository;
    }

    @PostConstruct
    private void generateData() {
        List<User> users = new ArrayList<>();
        users.add(new User("123456789"));
        users.add(new User("987654321"));
        users = (ArrayList) userRepository.save(users);
        List<Journey> journeys = new ArrayList<>();
        journeys.add(new Journey("123456789", 240));
        journeys.add(new Journey("123456789", 250));
        journeys.add(new Journey("987654321", 240));
        journeys.add(new Journey("987654321", 270));
        journeys.add(new Journey("987654321", 290));

        users.get(0).setJourneyIds(Arrays.asList(journeys.get(0).getId().toHexString()));
        users.get(0).setJourneyIds(Arrays.asList(journeys.get(1).getId().toHexString()));
        users.get(1).setJourneyIds(Arrays.asList(journeys.get(2).getId().toHexString()));
        users.get(1).setJourneyIds(Arrays.asList(journeys.get(3).getId().toHexString()));
        users.get(1).setJourneyIds(Arrays.asList(journeys.get(4).getId().toHexString()));

        userRepository.save(users);
    }
}