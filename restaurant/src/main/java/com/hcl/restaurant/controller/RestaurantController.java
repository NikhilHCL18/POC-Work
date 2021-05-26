package com.hcl.restaurant.controller;

import com.hcl.restaurant.model.Restaurant;
import com.hcl.restaurant.repository.RestaurantRepository;
import com.hcl.restaurant.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RestaurantController {

    @Autowired
    private RestaurantRepository  restaurantRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;


    @GetMapping("/restaurant")
    public List<Restaurant> getAllRestaurant(){
        System.out.println("Inside get all Restaurant");
        return restaurantRepository.findAll();

    }

    @PostMapping(value = "/restaurants")
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant){
        System.out.println("Inside the post method of Controller");
        restaurant.setId(sequenceGeneratorService.generateSequence(Restaurant.SEQUENCE_NAME));
        return restaurantRepository.save(restaurant);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable(value="id") Long restaurantId) throws ResourceNotFoundException{
        System.out.println("Inside the get method of getRestaurantById");
        Restaurant restaurant= restaurantRepository.findById(restaurantId).
                orElseThrow(()-> new ResourceNotFoundException("Restaurant Not found for this id::"+restaurantId));
        return ResponseEntity.ok().body(restaurant);
    }

    @PutMapping("/restaurant/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable(value="id") Long restaurantId , @RequestBody Restaurant restaurantDetails) throws ResourceNotFoundException{
        System.out.println("Inside the uodate method of updateRestaurant");
        Restaurant restaurant= restaurantRepository.findById(restaurantId).
                orElseThrow(()-> new ResourceNotFoundException("Restaurant Not found for this id::"+restaurantId));

        restaurant.setRestaurantName(restaurantDetails.getRestaurantName());
        final Restaurant updatedRestaurant=restaurantRepository.save(restaurant);
        return ResponseEntity.ok().body(updatedRestaurant);
    }

    @DeleteMapping("/restaurant/{id}")
    public Map<String,Boolean> deleteRestaurant(@PathVariable(value="id") Long restaurantId) throws ResourceNotFoundException{
        System.out.println("Inside the delete method of deleteRestaurant");
        Restaurant restaurant= restaurantRepository.findById(restaurantId).
                orElseThrow(()-> new ResourceNotFoundException("Restaurant Not found for this id::"+restaurantId));

        restaurantRepository.delete(restaurant);
        Map<String,Boolean> response= new HashMap<String,Boolean>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }
}
