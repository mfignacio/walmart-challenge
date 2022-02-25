package com.walmart.challenge.service.impl;

import com.walmart.challenge.dao.IProductDao;
import com.walmart.challenge.model.Product;
import com.walmart.challenge.service.IProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.IntStream;

import static com.walmart.challenge.utils.Utils.MINIMUM_AMOUNT_OF_CHARS_PER_SEARCH;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    @Override
    public List<Product> getProductByDescription(String description) {
        return productDao.findProductByDescription(description);
    }

    @Override
    public List<Product> getProductByBrand(String brand) {
        return productDao.findProductByBrand(brand);
    }

    @Override
    public List<Product> getProductByBrandAndDescription(String search) throws
            ResponseStatusException {
        if (!(MINIMUM_AMOUNT_OF_CHARS_PER_SEARCH == search.length())) {
            //throw new LessCharactersThanExpectedInSearchException(                    "Minimum amount of chars for searching is three (3). Please search again using at least three (3) characters.");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Minimum amount of chars for searching is three (3). Please search again using at least three (3) characters.");
        } else {
            return productDao.getProductByBrandAndDescription(search);
        }
    }

    @Override
    public Product getProductById(int id) {
        return productDao.findProductById(id);
    }

    @Override
    public boolean isPalindrome(String text) {
        if (text.length() >= MINIMUM_AMOUNT_OF_CHARS_PER_SEARCH) {
            String temp = text.replaceAll("\\s+", "").toLowerCase();
            return IntStream.range(0, temp.length() / 2)
                    .noneMatch(i -> temp.charAt(i) != temp.charAt(temp.length() - i - 1));
        } else {
            return false;
        }
    }

    @Override
    public void applyDiscount(List<Product> searchResult) {
        searchResult.forEach(result -> result.setPrice(result.getPrice() / 2));
    }

    @Override
    public void applyDiscountToSingleProduct(Product searchResult) {
        searchResult.setPrice(searchResult.getPrice() / 2);
    }
}
