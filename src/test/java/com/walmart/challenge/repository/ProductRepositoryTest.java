package com.walmart.challenge.repository;

import com.walmart.challenge.model.Product;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataMongoTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldFindProductByIdSuccessfully() {
        Product foundProduct = productRepository.findFirstProductById(1);

        assertEquals(foundProduct.get_id(), new ObjectId("6217cf868647b48a2f904bb0"));
        assertEquals(foundProduct.getId(), 1);
        assertEquals(foundProduct.getBrand(), "ooy eqrceli");
        assertEquals(foundProduct.getDescription(), "rlñlw brhrka");
        assertEquals(foundProduct.getImage(), "www.lider.cl/catalogo/images/whiteLineIcon.svg");
        assertEquals(foundProduct.getPrice(), 498724);

    }

    @Test
    void shouldGetProductByBrandAndDescriptionSuccessfully() {
        List<Product> productList = productRepository.getProductByBrandAndDescription("saas");

        assertTrue(productList.size() > 0);
        assertEquals(productList.get(0).get_id(), new ObjectId("6217cf868647b48a2f904bb1"));
        assertEquals(productList.get(0).getPrice(), 130173);
        assertEquals(productList.get(0).getImage(), "www.lider.cl/catalogo/images/babyIcon.svg");
        assertEquals(productList.get(0).getDescription(), "zlrwax bñyrh");
        assertEquals(productList.get(0).getBrand(), "dsaasd");

    }
}