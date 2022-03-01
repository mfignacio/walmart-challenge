package com.walmart.challenge.controller;

import com.walmart.challenge.model.Product;
import com.walmart.challenge.service.IProductService;

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

    private IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("getAllProducts/")
    public @ResponseBody
    Iterable<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("getProductById/{id}")
    public @ResponseBody
    Product getProductById(@PathVariable(value = "id") int id) {
        Product searchResult = productService.getProductById(id);
        if (searchResult != null && productService.shouldApplyDiscount(String.valueOf(id))) {
            searchResult = productService.applyDiscountToSingleProduct(searchResult);
        }
        return searchResult;
    }

    @GetMapping("getProductByBrandAndDescription/{search}")
    public @ResponseBody
    Iterable<Product> getProductByBrandAndDescription(@PathVariable(value = "search") String search) throws
            ResponseStatusException {

        List<Product> searchResult = productService.getProductByBrandAndDescription(search);
        if ((!searchResult.isEmpty()) && productService.shouldApplyDiscount(search)) {
            productService.applyDiscountToProductList(searchResult);
        }
        return searchResult;
    }
}
