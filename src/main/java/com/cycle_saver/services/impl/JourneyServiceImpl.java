package com.cycle_saver.services.impl;

import com.cycle_saver.model.user.Journey;
import com.cycle_saver.repositories.JourneyRepository;
import com.cycle_saver.services.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class JourneyServiceImpl implements JourneyService {
    private final JourneyRepository journeyRepository;

    @Autowired
    JourneyServiceImpl(JourneyRepository journeyRepository) {
        this.journeyRepository = journeyRepository;
    }

    @Override
    public List<Journey> findAllUserJourneys(List<String> ids) {
        return journeyRepository.findByIdIn(ids);
    }
}
