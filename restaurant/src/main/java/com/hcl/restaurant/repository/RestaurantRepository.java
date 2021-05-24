package com.hcl.restaurant.repository;

import com.hcl.restaurant.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant,Long> {


}
