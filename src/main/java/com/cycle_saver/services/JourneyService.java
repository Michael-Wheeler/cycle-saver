package com.cycle_saver.services;

import com.cycle_saver.model.user.Journey;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JourneyService {
    List<Journey> findAllUserJourneys(List<String> userId);
}
