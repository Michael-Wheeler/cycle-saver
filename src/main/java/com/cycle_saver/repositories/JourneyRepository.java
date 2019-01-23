package com.cycle_saver.repositories;

import com.cycle_saver.model.user.Journey;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JourneyRepository extends MongoRepository<Journey, ObjectId> {
    List<Journey> findByIdIn(List<String> ids);
}
