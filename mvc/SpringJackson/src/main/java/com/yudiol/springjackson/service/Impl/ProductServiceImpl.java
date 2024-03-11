package com.yudiol.springjackson.service.Impl;

import com.yudiol.springjackson.exception.errors.NotFoundException;
import com.yudiol.springjackson.model.Product;
import com.yudiol.springjackson.repository.ProductRepository;
import com.yudiol.springjackson.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product get(Long productId) {
        return productRepository.findByProductId(productId)
                .orElseThrow(() -> new NotFoundException(String.format("Продукт с id= '%s' не найден", productId)));
    }

    @Transactional
    public Long create(Product product) {
        return productRepository.save(product).getProductId();
    }

    @Transactional
    public Long update(Long productId, Product updatedProduct) {
        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new NotFoundException(String.format("Продукт с id= '%s' не найден", productId)));
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setQuantityInStock(updatedProduct.getQuantityInStock());
        return productRepository.save(product).getProductId();
    }

    @Transactional
    public void delete(Long productId) {
        productRepository.deleteByProductId(productId);
    }
}
