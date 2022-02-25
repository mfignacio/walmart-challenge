package com.walmart.challenge.dao;

import com.walmart.challenge.model.Product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface IProductDao extends MongoRepository<Product, String> {

    Product findProductById(int id);

    @Query("{description:'?0'}")
    List<Product> findProductByDescription(String name);

    @Query("{brand:'?0'}")
    List<Product> findProductByBrand(String brand);

    @Query(value = "{}", fields = "{'id' : 1, 'brand' : 1, 'description' : 1, 'image' : 1, 'price' : 1}")
    List<Product> findAll();

    @Query("{'$or':[ {'brand':{'$regex' : ?0, '$options' : 'i'}}, {'description':{'$regex' : ?0, '$options' : 'i'}} ]}")
    List<Product> getProductByBrandAndDescription(String search);
}