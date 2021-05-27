package com.hcl.restaurant.repository;

import com.hcl.restaurant.model.MenuItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MenuRepository extends MongoRepository<MenuItem,Long> {
    List<MenuItem> findByRestaurantId(Long restaurantId);

}
