package com.walmart.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walmart.challenge.model.Product;
import com.walmart.challenge.service.impl.ProductServiceImpl;
import com.walmart.challenge.utils.Utils;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @Mock
    private ProductServiceImpl productService;
    private Product product;
    private Product product2;
    private List<Product> productList;
    @InjectMocks
    private ProductController productController;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        product = new Product(new ObjectId(),
                              1,
                              "ooy eqrceli",
                              "dsaasd",
                              "www.lider.cl/catalogo/images/whiteLineIcon.svg",
                              498724);
        product2 = new Product(new ObjectId(),
                               121,
                               "ooy eqrceli",
                               "dsaasd",
                               "www.lider.cl/catalogo/images/whiteLineIcon.svg",
                               498724);
        productList = new ArrayList<>();
        productList.add(product);
        productList.add(product2);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void shouldGetAllProductsSuccessfully() throws Exception {
        when(productService.getAllProducts()).thenReturn(productList);
        mockMvc.perform(get("/getAllProducts/").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(product))).
                andDo(MockMvcResultHandlers.print());
        verify(productService).getAllProducts();
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void shouldGetASingleProductSuccessfully() throws Exception {
        when(productService.getProductById(product.getId())).thenReturn(product);
        mockMvc.perform(get("/getProductById/1").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(product))).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }

    @Test
    void shouldGetASingleProductWithDiscountSuccessfully() throws Exception {
        when(productService.getProductById(product2.getId())).thenReturn(product2);
        when(Utils.isPalindrome("121")).thenReturn(true);
        mockMvc.perform(get("/getProductById/121").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(product2))).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }

    @Test
    void shouldProductListSuccessfully() throws Exception {
        when(productService.getProductById(product2.getId())).thenReturn(product2);
        when(Utils.isPalindrome("121")).thenReturn(true);
        mockMvc.perform(get("/getProductById/121").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(product2))).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}