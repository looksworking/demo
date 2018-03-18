package com.example.demo;

import com.example.demo.model.ProductDetails;
import com.example.demo.model.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class ProductWebController {

    Logger logger = LoggerFactory.getLogger(ProductWebController.class);

    @Autowired
    ProductService productService;

    @GetMapping({"/", "/search"})
    public String index(Model model) {

        return "home";
    }

    @GetMapping("/delete/{productId}")
    public String delete(@PathVariable("productId") UUID productId, Model model) {
        productService.deleteById(productId);
        return "home";
    }

    @PostMapping("/search")
    public String search(@ModelAttribute("search") String search, Model model) {

        logger.info("Searching \'{}\'", search);
        List<ProductDetails> products = productService.findAllByBrandOrName(search);
        logger.info("Found {} results", products.size());
        model.addAttribute("products", products);

        return "home";
    }

    @GetMapping("/add")
    public String addForm(Model model) {

        model.addAttribute("product", new ProductDetails());
        return "product";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute ProductDetails productDetails) {

        productService.add(productDetails);
        return "home";
    }

    @GetMapping("/edit/{productId}")
    public String editForm(@PathVariable("productId") UUID productId, Model model) {

        ProductDetails productDetails = productService.getOne(productId);
        model.addAttribute("product", productDetails);
        return "product";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute ProductDetails productDetails) {

        productService.update(productDetails);
        return "home";
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @PostMapping("/login")
    public String auth() {

        return "home";
    }

}
