package com.hcl.restaurant.service;

import com.hcl.restaurant.model.Restaurant;
import com.hcl.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurant(){
        return restaurantRepository.findAll();
    }

    public Restaurant createRestaurant(Restaurant restaurant){
        return restaurantRepository.save(restaurant);
    }

    public Restaurant getRestaurantById(Long restaurantId){
        return restaurantRepository.findById(restaurantId).
                orElseThrow(()-> new ResourceNotFoundException
                        ("Restaurant Not found for this id::"+restaurantId));
    }


}
