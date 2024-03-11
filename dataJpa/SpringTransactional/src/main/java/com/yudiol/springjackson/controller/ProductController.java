package com.yudiol.springjackson.controller;

import com.yudiol.springjackson.model.Product;
import com.yudiol.springjackson.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@ResponseStatus(HttpStatus.OK)
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{productId}")
    public Product get(@PathVariable Long productId) {
        return productService.findByProductId(productId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Long create(@RequestBody Product product) {
        return productService.create(product);
    }

    @PatchMapping("/{productId}")
    public void update(@PathVariable Long productId, @RequestBody Product product) {
        productService.update(productId, product);
    }

    @DeleteMapping("/{productId}")
    public void  delete(@PathVariable Long productId){
        productService.delete(productId);
    }
}
