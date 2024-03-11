package com.yudiol.springjackson.service.Impl;

import com.yudiol.springjackson.exception.errors.NotFoundException;
import com.yudiol.springjackson.model.Product;
import com.yudiol.springjackson.repository.ProductRepository;
import com.yudiol.springjackson.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Product findByProductId(Long productId) {
        return productRepository.findByProductId(productId)
                .orElseThrow(() -> new NotFoundException("Продукт ", String.valueOf(productId)));
    }

    @Transactional
    public Long create(Product product) {
        return productRepository.save(product).getProductId();
    }

    @Transactional
    public void update(Long productId, Product updatedProduct) {
        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new NotFoundException("Продукт ", String.valueOf(productId)));

        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setQuantityInStock(updatedProduct.getQuantityInStock());
        productRepository.save(product);
    }

    @Transactional
    public void delete(Long productId) {
        productRepository.deleteByProductId(productId);

    }


}
