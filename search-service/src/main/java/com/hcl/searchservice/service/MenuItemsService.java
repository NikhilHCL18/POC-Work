package com.hcl.searchservice.service;

import com.hcl.searchservice.model.MenuItems;
import org.springframework.data.elasticsearch.core.SearchHits;


public interface MenuItemsService {

    String Save(MenuItems menuItems);
    SearchHits<MenuItems> searchMultiField(String name, int qty);
    SearchHits<MenuItems> getMenuNameSearchData(String input);
    SearchHits<MenuItems> multiMatchQuery(Long id);
    SearchHits<MenuItems> getMenuItemsByPriceRange(final Double itemPriceMin, final Double itemPriceMax);
    SearchHits<MenuItems> getMenuItemsByDescriptions(final String descriptions);
}
