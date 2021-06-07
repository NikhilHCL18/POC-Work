package com.hcl.searchservice.controller;

import com.hcl.searchservice.model.MenuItems;
import com.hcl.searchservice.service.MenuItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/rest/dslSearch")
public class DSLSearchController {
    @Autowired
    private MenuItemsService menuItemsService;

    //Save
    @PostMapping("/menuItems")
    public String saveItems(@RequestBody MenuItems menuItems) {
        return menuItemsService.Save(menuItems);
    }


    //Name And Quantity
    @GetMapping("/nameandquantity/{name}/{quantity}")
    public SearchHits<MenuItems> getProductsByNameAndQuantity(
            @PathVariable String name, @PathVariable int quantity){
        return menuItemsService.searchMultiField(name, quantity);
    }

    //By itemName specifically for wildcard characters
    @GetMapping("/wildcard/{searchString}")
    public SearchHits<MenuItems> getMenuNameSearchData(
            @PathVariable String searchString){
        return menuItemsService.getMenuNameSearchData(searchString);
    }

    //Descriptions
    @GetMapping("/description/{descriptions}")
    public SearchHits<MenuItems> getMenuItemsByDescriptions(
            @PathVariable String descriptions){
        return menuItemsService.getMenuItemsByDescriptions(descriptions);
    }

    //By restaurantId Or ItemId
    @GetMapping("/restaurantIdOrItemId/{id}")
    public SearchHits<MenuItems> getMenuItemsByRestaurantIdOrItemId(
            @PathVariable Long id){
        return menuItemsService.multiMatchQuery(id);
    }

    //Items price range
    @GetMapping("/itemsPriceRange/{priceMin}/{priceMax}")
    public SearchHits<MenuItems> getProductsByPriceRange(
            @PathVariable Double priceMin, @PathVariable Double priceMax){
        return menuItemsService.getMenuItemsByPriceRange(priceMin, priceMax);
    }


}
