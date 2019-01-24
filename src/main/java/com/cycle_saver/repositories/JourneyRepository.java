package com.cycle_saver.repositories;

import com.cycle_saver.model.user.Journey;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JourneyRepository extends MongoRepository<Journey, String> {
    List<Journey> findByIdIn(List<String> ids);
}
