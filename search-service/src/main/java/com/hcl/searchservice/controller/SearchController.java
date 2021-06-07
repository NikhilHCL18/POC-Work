package com.hcl.searchservice.controller;

import com.hcl.searchservice.model.MenuItems;
import com.hcl.searchservice.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/search")
public class SearchController {

    @Autowired
    MenuItemRepository menuItemRepository;

    @GetMapping(value = "/name/{itemName}")
    public List<MenuItems> searchName(@PathVariable final String itemName) {
        return menuItemRepository.findByName(itemName);
    }

    @GetMapping(value = "/{itemId}")
    public Optional<MenuItems> searchId(@PathVariable final Long itemId) {
        return menuItemRepository.findById(itemId.toString());
    }

    @GetMapping(value = "/all")
    public List<MenuItems> searchAll() {
        List<MenuItems> itemList = new ArrayList<>();
        Iterable<MenuItems> items = menuItemRepository.findAll();
        items.forEach(itemList::add);
        return itemList;
    }

}
