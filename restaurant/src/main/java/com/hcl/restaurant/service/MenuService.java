package com.hcl.restaurant.service;

import com.hcl.restaurant.model.MenuItem;
import com.hcl.restaurant.model.Restaurant;
import com.hcl.restaurant.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuRepository menuRepository;

    public List<MenuItem> createMenu(List<MenuItem> menuItems, Long restaurantId) {
        try {
            Restaurant restaurantDetails = restaurantService.getRestaurantById(restaurantId);
            if (restaurantDetails != null) {
                List<MenuItem> data = menuItems.stream().map(item -> {
                    item.setRestaurantId(restaurantId);
                    return item;
                }).collect(Collectors.toList());

                return menuRepository.saveAll(data);
            }
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();

    }

    public List<MenuItem> getMenu(Long restaurantId) {
        try {
            Restaurant restaurantDetails = restaurantService.getRestaurantById(restaurantId);
            if (restaurantDetails != null) {
                return menuRepository.findByRestaurantId(restaurantId);
            }
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();

    }
}
