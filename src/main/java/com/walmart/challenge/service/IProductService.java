package com.walmart.challenge.service;

import com.walmart.challenge.model.Product;

import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();

    List<Product> getProductByDescription(String description);

    List<Product> getProductByBrand(String description);

    List<Product> getProductByBrandAndDescription(String search) throws ResponseStatusException;

    Product getProductById(int id);

    void applyDiscountToProductList(List<Product> searchResult);

    void applyDiscountToSingleProduct(Product searchResult);
}
