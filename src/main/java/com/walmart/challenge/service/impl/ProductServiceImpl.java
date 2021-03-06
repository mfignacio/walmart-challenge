package com.walmart.challenge.service.impl;

import com.walmart.challenge.model.Product;
import com.walmart.challenge.repository.ProductRepository;
import com.walmart.challenge.service.IProductService;
import com.walmart.challenge.utils.Constants;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class ProductServiceImpl implements IProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductByBrandAndDescription(String search) throws
            ResponseStatusException {
        if (!(search.length() >= Constants.MINIMUM_AMOUNT_OF_CHARS_PER_SEARCH)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Minimum amount of chars for searching is three (3). Please search again using at least three (3) characters.");
        } else {
            return productRepository.getProductByBrandAndDescription(search);
        }
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findFirstProductById(id);
    }

    @Override
    public List<Product> applyDiscountToProductList(List<Product> searchResult) {
        searchResult.forEach(result -> result.setPrice(result.getPrice() / 2f));
        return searchResult;
    }

    @Override
    public Product applyDiscountToSingleProduct(Product searchResult) {
        searchResult.setPrice(searchResult.getPrice() / 2f);
        return searchResult;
    }

    @Override
    public boolean shouldApplyDiscount(String search) {
        return isPalindrome(search);
    }

    private boolean isPalindrome(String text) {
        if (text.length() >= Constants.MINIMUM_AMOUNT_OF_CHARS_PER_SEARCH) {
            String temp = text.replaceAll("\\s+", "").toLowerCase();
            return IntStream.range(0, temp.length() / 2)
                    .noneMatch(i -> temp.charAt(i) != temp.charAt(temp.length() - i - 1));
        } else {
            return false;
        }
    }
}
