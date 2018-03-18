package com.example.demo;

import com.example.demo.model.ProductDetails;
import com.example.demo.model.ProductList;
import com.example.demo.model.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
public class ProductRestController {

    @Autowired
    ProductService productService;

    @GetMapping("/products/search/{searchString}")
    public ResponseEntity<Object> findProductsByBrandOrName(@PathVariable("searchString") String searchString) {

        List<ProductDetails> productDetailsEntities = productService.findAllByBrandOrName(searchString);
        return new ResponseEntity<>(new ProductList(productDetailsEntities), HttpStatus.OK);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDetails> getProduct(@PathVariable("productId") UUID productId) {

        return new ResponseEntity<>(productService.getOne(productId), HttpStatus.OK);
    }

    @PostMapping("/products/")
    public ResponseEntity<Object> addProduct(@RequestBody ProductDetails productDetails) {

        ProductDetails added = productService.add(productDetails);
        return new ResponseEntity<>(added, HttpStatus.CREATED);
    }

    @PutMapping("/products/")
    public ResponseEntity<Object> updateProduct(@RequestBody ProductDetails productDetails) {

        ProductDetails updated = productService.update(productDetails);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("productId") UUID productId) {

        productService.deleteById(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/products/leftovers")
    public ResponseEntity<ProductList> getLeftovers() {

        ProductList productList = productService.getLeftovers();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

}
