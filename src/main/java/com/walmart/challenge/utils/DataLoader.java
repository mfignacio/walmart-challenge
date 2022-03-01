package com.walmart.challenge.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.walmart.challenge.model.Product;
import com.walmart.challenge.repository.ProductRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

@Component
public class DataLoader {

    @Value("classpath:data/init-data.json")
    Resource resourceFile;

    private ProductRepository productRepository;

    public DataLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void loadData() throws IOException {
        loadSampleData();
    }

    private void loadSampleData() throws IOException {
        if (productRepository.findAll().isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            List<Product> productList = mapper.readValue(new ClassPathResource(resourceFile.getFilename()).getInputStream(),
                                                         new TypeReference<List<Product>>() {
                                                         });
            productList.forEach(product -> product.set_id(new ObjectId()));
            productList.forEach(product -> productRepository.save(product));
        }
    }

}
