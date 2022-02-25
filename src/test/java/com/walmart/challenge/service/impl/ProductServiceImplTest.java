package com.walmart.challenge.service.impl;

import com.walmart.challenge.model.Product;
import com.walmart.challenge.repository.ProductRepository;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Autowired
    @InjectMocks
    private ProductServiceImpl productService;
    private Product product1;
    private Product product2;

    List<Product> productList;

    @BeforeEach
    public void setUp() {
        productList = new ArrayList<>();
        product1 = new Product(new ObjectId(),
                               1,
                               "ooy eqrceli",
                               "dsaasd",
                               "www.lider.cl/catalogo/images/whiteLineIcon.svg",
                               498724);
        product2 = new Product(new ObjectId(),
                               2,
                               "dsaasd",
                               "zlrwax bñyrh",
                               "www.lider.cl/catalogo/images/babyIcon.svg",
                               130173);

        productList.add(product1);
        productList.add(product2);
    }

    @Test
    void shouldGetAllProductsSuccessfully() {
        when(productRepository.findAll()).thenReturn(productList);

        List<Product> productList1 = productService.getAllProducts();

        assertEquals(productList1, productList);
        Mockito.verify(productRepository, times(1)).findAll();
    }

    @Test
    void shouldGetProductByIdSuccessfully() {
        Mockito.when(productRepository.findFirstProductById(1)).thenReturn(product1);

        Product productResult = productService.getProductById(1);

        assertThat(productService.getProductById(product1.getId())).isEqualTo(productResult);
    }

    @Test
    void shouldGetProductsByBrandAndDescriptionSuccessfully() {
        when(productRepository.getProductByBrandAndDescription("saas")).thenReturn(productList);

        List<Product> productList1 = productService.getProductByBrandAndDescription("saas");

        assertEquals(productList1, productList);
        Mockito.verify(productRepository, times(1)).getProductByBrandAndDescription("saas");
    }



    /*
    @Test
    void shouldApplyDiscountToProductListSuccessfully() {
        List<Product> originalProductList = productList;

        productService.applyDiscountToProductList(productList);

        for (int i = 0; i < productList.size(); i++) {
            assertEquals(productList.get(i).getPrice(), originalProductList.get(i).getPrice() / 2);
        }

    }

    @Test
    void shouldApplyDiscountSingleProductSuccessfully() {
        Product originalProduct = product1;
        productService.applyDiscountToSingleProduct(product1);
        assertEquals(product1.getPrice(), originalProduct.getPrice()/2);
    }
    */
}