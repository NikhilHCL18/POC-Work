package com.hcl.searchservice.repository;

import com.hcl.searchservice.model.MenuItems;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuItemRepository extends ElasticsearchRepository<MenuItems, String> {

    List<MenuItems> findByName(String name);
    Optional<MenuItems> findById(String itemId);
}
