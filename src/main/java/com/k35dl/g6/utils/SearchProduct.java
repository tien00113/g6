package com.k35dl.g6.utils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;

import com.k35dl.g6.models.Product.Product;

import java.util.List;

public class SearchProduct {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> searchByProductName(String productName) {
        SearchSession searchSession = Search.session(entityManager);

        List<Product> products = searchSession.search(Product.class)
                .where(f -> f.match()
                        .field("name")
                        .matching(productName))
                .fetchHits(20); // fetch the top 20 matches

        return products; // get the matched entities
    }
}
