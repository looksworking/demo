package com.example.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductDetails, UUID> {

    List<ProductDetails> findAllByBrandOrNameContaining(String s1, String s2);

    ProductDetails findByBrandAndName(String brand, String name);

    List<ProductDetails> findAllByQuantityLessThan(int leftover);

}
