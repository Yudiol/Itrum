package com.yudiol.springjackson.service;

import com.yudiol.springjackson.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product get(Long productId);
    Long create(Product product);
    Long update(Long productId,Product product);
    void delete(Long productId);
}
