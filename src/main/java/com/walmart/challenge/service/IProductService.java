package com.walmart.challenge.service;

import com.walmart.challenge.model.Product;

import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface IProductService {

    Product getProductById(int id);

    List<Product> getProductByBrandAndDescription(String search) throws ResponseStatusException;

    List<Product> applyDiscountToProductList(List<Product> searchResult);

    Product applyDiscountToSingleProduct(Product searchResult);

    boolean shouldApplyDiscount(String search);
}
