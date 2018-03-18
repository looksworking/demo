package com.example.demo;

import com.example.demo.model.ProductDetails;
import com.example.demo.model.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private ProductDetails productDetails;

    private UUID someProductId = UUID.randomUUID();

    private ObjectMapper objectMapper;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        this.objectMapper = new ObjectMapper();
        this.productDetails = new ProductDetails(this.someProductId);
    }

    @Test
    public void getProduct_productExists() throws Exception {

        when(this.productService.getOne(this.someProductId)).thenReturn(this.productDetails);

        this.mockMvc
                .perform(get("/products/" + this.someProductId.toString()))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.productId")
                        .value(this.someProductId.toString()));
    }

    @Test
    public void getProduct_noProductExists() throws Exception{
        // TODO
    }

    @Test
    public void addProduct_noProductExists() throws Exception {

        when(this.productService.add(this.productDetails)).thenReturn(this.productDetails);

        this.mockMvc
                .perform(post("/products/")
                        .content(this.objectMapper.writeValueAsBytes(this.productDetails))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.productId")
                        .value(this.someProductId.toString()));
    }

    @Test
    public void addProduct_productExists() throws Exception {
        // TODO
    }

    @Test
    public void updateProduct_productExists() throws Exception {
        // TODO
    }

    @Test
    public void updateProduct_noProductExists() throws Exception {
        // TODO
    }
}
