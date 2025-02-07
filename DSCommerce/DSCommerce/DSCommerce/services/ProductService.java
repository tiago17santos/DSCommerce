package com.techverse.DSCommerce.services;

import com.techverse.DSCommerce.dtos.ProductDto;
import com.techverse.DSCommerce.entities.Product;
import com.techverse.DSCommerce.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDto findById(long id) {
        Product product = productRepository.findById(id).get();
        return new ProductDto(product);
    }

    public List<ProductDto> findAll() {
        List<Product> result = productRepository.findAll();
        return result.stream().map(ProductDto::new).toList();
    }

    public void save(ProductDto productDto) {
        P
    }
}
