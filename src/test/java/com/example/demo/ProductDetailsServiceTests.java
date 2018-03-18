package com.example.demo;

import com.example.demo.model.ProductDetails;
import com.example.demo.model.ProductRepository;
import com.example.demo.model.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductDetailsServiceTests {

    @MockBean
    ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Captor
    private ArgumentCaptor<ProductDetails> entityArgumentCaptor;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addProduct_noProductExists() throws Exception {

        productService.add(getProductEntity());
        verify(productRepository).save(entityArgumentCaptor.capture());
        assertEquals(getProductEntity().getBrand(), entityArgumentCaptor.getValue().getBrand());
    }

    @Test(expected = EntityExistsException.class)
    public void addProduct_productExists() throws Exception {

        when(productRepository
                .findByBrandAndName(getProductEntity().getBrand(), getProductEntity().getName()))
                .thenReturn(getProductEntity());

        productService.add(getProductEntity());
    }

    @Test
    public void updateProduct_productExists() throws Exception {

        int expectedQty = 20;

        when(productRepository
                .findByBrandAndName(getProductEntity().getBrand(), getProductEntity().getName()))
                .thenReturn(getProductEntity());

        ProductDetails productDetails = getProductEntity();
        productDetails.setQuantity(expectedQty);

        productService.update(productDetails);
        verify(productRepository).save(entityArgumentCaptor.capture());
        assertEquals(expectedQty, entityArgumentCaptor.getValue().getQuantity());
    }

    @Test(expected = EntityNotFoundException.class)
    public void updateProduct_noProductExists() throws Exception {

        productService.update(getProductEntity());
    }


    @Test
    public void getProduct_productExists() throws Exception {

    }

    private ProductDetails getProductEntity() {
        ProductDetails productDetails = new ProductDetails();
        productDetails.setBrand("Brand");
        productDetails.setName("Name");
        productDetails.setPrice(new BigDecimal(10));
        productDetails.setQuantity(10);
        return productDetails;
    }
}
