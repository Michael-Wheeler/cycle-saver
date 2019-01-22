package com.cycle_saver.repositories;

import com.cycle_saver.model.user.Journey;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface JourneyRepository extends PagingAndSortingRepository<Journey, ObjectId> {
    List<Journey> findByIdIn(List<String> ids);
}
