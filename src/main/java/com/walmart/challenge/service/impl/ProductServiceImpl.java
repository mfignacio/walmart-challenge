package com.walmart.challenge.service.impl;

import com.walmart.challenge.model.Product;
import com.walmart.challenge.repository.ProductRepository;
import com.walmart.challenge.service.IProductService;
import com.walmart.challenge.utils.Constants;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    private ProductRepository productDao;

    public ProductServiceImpl(ProductRepository productDao) {
        this.productDao = productDao;
    }

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
        if (!(search.length() >= Constants.MINIMUM_AMOUNT_OF_CHARS_PER_SEARCH)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Minimum amount of chars for searching is three (3). Please search again using at least three (3) characters.");
        } else {
            return productDao.getProductByBrandAndDescription(search);
        }
    }

    @Override
    public Product getProductById(int id) {
        return productDao.findFirstProductById(id);
    }

    @Override
    public void applyDiscountToProductList(List<Product> searchResult) {
        searchResult.forEach(result -> result.setPrice(result.getPrice() / 2));
    }

    @Override
    public void applyDiscountToSingleProduct(Product searchResult) {
        searchResult.setPrice(searchResult.getPrice() / 2);
    }
}
