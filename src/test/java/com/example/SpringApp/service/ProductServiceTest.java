package com.example.SpringApp.service;

import com.example.SpringApp.model.Product;
import com.example.SpringApp.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl productServiceImpl;
    private Product product1;
    private Product product2;
    List<Product> productList;

    @BeforeEach
    public void setup(){
        productList = new ArrayList<>();
        product1 = new Product(1L, "P1234", "Samsung Galaxy S21", 500, "Special",
                "Samsung", "Samsung", 1000, 500, false, "none" );
        product2 = new Product(2L, "P4321", "Samsung Galaxy A23", 200, "Special",
                "Samsung", "Samsung", 1500, 400, false, "none" );
        productList.add(product1);
        productList.add(product2);
    }

    @AfterEach
    public void tearDown() {
        product1 = product2 = null;
        productList = null;
    }

    @Test
    public void save_product_test(){
        when(productRepository.save(any())).thenReturn(product1);
        productServiceImpl.save(product1);
        verify(productRepository,times(1)).save(any());
    }

    @Test
    public void get_all_products_test(){
        productRepository.save(product1);
        //stubbing mock to return specific data
        when(productRepository.findAll()).thenReturn(productList);
        List<Product> productList1 =productServiceImpl.getAllProducts();
        assertEquals(productList1,productList);
        verify(productRepository,times(1)).save(product1);
        verify(productRepository,times(1)).findAll();
    }

    @Test
    public void get_product_by_id_test(){
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(product1));
        assertThat(productServiceImpl.getProductById(product1.getId())).isEqualTo(product1);
    }

    @Test
    public void find_by_item_number_test(){
        Mockito.when(productRepository.findByItemNumber("P1234")).thenReturn(product1);
        assertThat(productServiceImpl.findByItemNumber(product1.getItemNumber())).isEqualTo(product1);

    }

    @Test
    public void delete_product_by_id_test(){
        productRepository.save(product1);
        productRepository.deleteById(product1.getId());
        Optional optional = productRepository.findById(product1.getId());
        assertEquals(Optional.empty(), optional);
    }

}