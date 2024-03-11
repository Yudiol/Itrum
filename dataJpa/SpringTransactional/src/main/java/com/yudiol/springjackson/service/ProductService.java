package com.yudiol.springjackson.service;

import com.yudiol.springjackson.model.Product;

public interface ProductService {
    Product findByProductId(Long productId);

    Long create(Product product);

    void update(Long productId, Product product);

    void delete(Long productId);
}
