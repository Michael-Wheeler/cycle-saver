package com.cycle_saver.services.impl;

import com.cycle_saver.model.user.Journey;
import com.cycle_saver.repositories.JourneyRepository;
import com.cycle_saver.services.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JourneyServiceImpl implements JourneyService {
    @Autowired
    private JourneyRepository journeyRepository;

//    JourneyServiceImpl(JourneyRepository journeyRepository) {
//        this.journeyRepository = journeyRepository;
//    }

    @Override
    public List<Journey> saveAll(List<Journey> journeys) {
        return journeyRepository.saveAll(journeys);
    }

    @Override
    public List<Journey> findAllUserJourneys(List<String> ids) {
        return journeyRepository.findByIdIn(ids);
    }
}
