package com.walmart.challenge.repository;

import com.walmart.challenge.model.Product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findAll();

    Product findFirstProductById(int id);

    @Query("{'$or':[ {'brand':{'$regex' : ?0, '$options' : 'i'}}, {'description':{'$regex' : ?0, '$options' : 'i'}} ]}")
    List<Product> getProductByBrandAndDescription(String search);
}