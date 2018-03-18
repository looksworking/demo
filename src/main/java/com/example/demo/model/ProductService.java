package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public List<ProductDetails> findAllByBrandOrName(String s1) {
        if (s1 == null) {
            return productRepository.findAll();
        }
        return productRepository.findAllByBrandOrNameContaining(s1, s1);
    }

    public ProductDetails update(ProductDetails productDetails) {

        ProductDetails exists = productRepository
                .findByBrandAndName(productDetails.getBrand(), productDetails.getName());

        if (exists == null) {
            throw new EntityNotFoundException();
        }

        return productRepository.save(productDetails);
    }

    public ProductDetails add(ProductDetails productDetails) {

        ProductDetails exists = productRepository
                .findByBrandAndName(productDetails.getBrand(), productDetails.getName());

        if (exists != null) {
            throw new EntityExistsException();
        }

        return productRepository.save(productDetails);
    }

    public ProductDetails getOne(UUID productId) {

        ProductDetails productDetails = productRepository.getOne(productId);

        if (productDetails == null) {
            throw new EntityNotFoundException();
        }

        return productDetails;
    }

    public void deleteById(UUID productId) {
        productRepository.deleteById(productId);
    }

    public ProductList getLeftovers() {

        List<ProductDetails> productDetailsEntities = productRepository.findAllByQuantityLessThan(5);
        return new ProductList(productDetailsEntities);
    }
}
