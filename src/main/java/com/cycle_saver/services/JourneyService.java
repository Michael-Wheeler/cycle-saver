package com.cycle_saver.services;

import com.cycle_saver.model.user.Journey;

import java.util.List;

public interface JourneyService {
    List<Journey> findAllUserJourneys(List<String> userId);
}
