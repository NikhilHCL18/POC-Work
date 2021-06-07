package com.hcl.searchservice.service;

import com.hcl.searchservice.model.MenuItems;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;


@Service
public class MenuItemsServiceImpl implements MenuItemsService{

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Value("${elasticsearch.index.name}")
    private String indexName;

    @Value("${elasticsearch.items.type}")
    private String itemTypeName;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Override
    public String Save(MenuItems menuItems) {
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(menuItems.getId().toString())
                .withObject(menuItems)
                .build();
        return elasticsearchOperations.index(indexQuery,IndexCoordinates.of(indexName));
    }


    public SearchHits<MenuItems> searchMultiField(String name, int qty) {
        QueryBuilder query = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("name", name))
                .must(QueryBuilders.matchQuery("quantity", qty));
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(query).build();
        SearchHits<MenuItems> items = elasticsearchOperations.search(nativeSearchQuery, MenuItems.class, IndexCoordinates.of(indexName));
        return items;
    }

    public SearchHits<MenuItems> getMenuNameSearchData(String name) {
        String search = ".*" + name + ".*";
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withFilter(QueryBuilders.regexpQuery("name", search)).build();
        SearchHits<MenuItems> items = elasticsearchOperations.search(nativeSearchQuery, MenuItems.class, IndexCoordinates.of(indexName));
        return items;

    }

    public SearchHits<MenuItems> multiMatchQuery(Long id) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.multiMatchQuery(id)
                .field("restaurantId").field("id").type(MultiMatchQueryBuilder.Type.BEST_FIELDS)).build();
        SearchHits<MenuItems> items = elasticsearchOperations.search(nativeSearchQuery, MenuItems.class, IndexCoordinates.of(indexName));
        return items;
    }


    public SearchHits<MenuItems> getMenuItemsByPriceRange(final Double itemPriceMin, final Double itemPriceMax) {
        Criteria criteria = new Criteria("price")
                .greaterThan(itemPriceMin)
                .lessThan(itemPriceMax);

        Query searchQuery = new CriteriaQuery(criteria);

        SearchHits<MenuItems> items = elasticsearchOperations.search(searchQuery, MenuItems.class, IndexCoordinates.of(indexName));
        return items;
    }


    public SearchHits<MenuItems> getMenuItemsByDescriptions(final String description) {
        Query searchQuery = new StringQuery(
                "{\"match\":{\"description\":{\"query\":\""+ description + "\"}}}\"");

        SearchHits<MenuItems> items = elasticsearchOperations.search(searchQuery, MenuItems.class, IndexCoordinates.of(indexName));
        return items;
    }

}
