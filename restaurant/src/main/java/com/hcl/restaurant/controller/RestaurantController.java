package com.hcl.restaurant.controller;

import com.hcl.restaurant.model.MenuItem;
import com.hcl.restaurant.model.Restaurant;
import com.hcl.restaurant.repository.RestaurantRepository;
import com.hcl.restaurant.service.MenuService;
import com.hcl.restaurant.service.RestaurantService;
import com.hcl.restaurant.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("restaurant/api")
public class RestaurantController {

    @Autowired
    private RestaurantRepository  restaurantRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;


    @GetMapping("/getAllRestaurant")
    public List<Restaurant> getAllRestaurant(){
        System.out.println("Inside get all Restaurant");
        return restaurantService.getAllRestaurant();
    }

    @PostMapping(value = "/createRestaurant")
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant){
        System.out.println("Inside the post method of Controller");
        restaurant.setId(sequenceGeneratorService.generateSequence(Restaurant.SEQUENCE_NAME));
        return restaurantService.createRestaurant(restaurant);
    }

    @GetMapping("/getRestaurantById/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable(value="id") Long restaurantId) throws ResourceNotFoundException{
        System.out.println("Inside the get method of getRestaurantById");
        Restaurant restaurant= restaurantService.getRestaurantById(restaurantId);
        return ResponseEntity.ok().body(restaurant);
    }

    @PostMapping("/create/{restaurantId}/menu")
    public ResponseEntity<List<MenuItem>> createMenu(@RequestBody List<MenuItem> menuList,
                                                     @PathVariable("restaurantId") Long restaurantId)
            throws ResourceNotFoundException {

        Restaurant restaurantDetails = restaurantService.getRestaurantById(restaurantId);
        if (restaurantDetails == null)
            throw new ResourceNotFoundException(
                    "Menu Creation Failed for Restaurant. restaurantId " + restaurantId);
        List<MenuItem> result = menuService.createMenu(menuList, restaurantId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/get/{restaurantId}/menu")
    public ResponseEntity<List<MenuItem>> getMenuDetails(@PathVariable("restaurantId") Long restaurantId)
            throws ResourceNotFoundException {
        List<MenuItem> menuList = menuService.getMenu(restaurantId);
        return new ResponseEntity<>(menuList, HttpStatus.OK);
    }

    @PutMapping("/updateRestaurant/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable(value="id") Long restaurantId , @RequestBody Restaurant restaurantDetails) throws ResourceNotFoundException{
        System.out.println("Inside the update method of updateRestaurant");
        Restaurant restaurant= restaurantRepository.findById(restaurantId).
                orElseThrow(()-> new ResourceNotFoundException("Restaurant Not found for this id::"+restaurantId));

        restaurant.setRestaurantName(restaurantDetails.getRestaurantName());
        final Restaurant updatedRestaurant=restaurantRepository.save(restaurant);
        return ResponseEntity.ok().body(updatedRestaurant);
    }

    @DeleteMapping("/removeRestaurant/{id}")
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
