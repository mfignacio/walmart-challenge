package com.walmart.challenge.controller;

import com.walmart.challenge.model.Product;
import com.walmart.challenge.service.IProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("getAllProducts/")
    public @ResponseBody
    Iterable<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("getProductByDescription/{description}")
    public @ResponseBody
    Iterable<Product> getProductByDescription(@PathVariable(value = "description") String description) {
        return productService.getProductByDescription(description);
    }

    @GetMapping("getProductByBrand/{brand}")
    public @ResponseBody
    Iterable<Product> getProductByBrand(@PathVariable(value = "brand") String brand) {
        return productService.getProductByBrand(brand);
    }

    @GetMapping("getProductByBrandAndDescription/{search}")
    public @ResponseBody
    Iterable<Product> getProductByBrandAndDescription(@PathVariable(value = "search") String search) throws
            ResponseStatusException {

        List<Product> searchResult = productService.getProductByBrandAndDescription(search);
        if(searchResult.size() > 0 && productService.isPalindrome(search)){
            productService.applyDiscount(searchResult);
        }
        return searchResult;
    }

    @GetMapping("getProductById/{id}")
    public @ResponseBody
    Product getProductById(@PathVariable(value = "id") int id) {
        Product searchResult = productService.getProductById(id);
        if(searchResult != null && productService.isPalindrome(String.valueOf(id))){
            productService.applyDiscountToSingleProduct(searchResult);
        }
        return searchResult;
    }
}
