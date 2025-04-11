package com.techverse.DSCommerce.services;

import com.techverse.DSCommerce.dtos.ProductDto;
import com.techverse.DSCommerce.entities.Product;
import com.techverse.DSCommerce.repositories.ProductRepository;
import com.techverse.DSCommerce.services.exceptions.DatabaseException;
import com.techverse.DSCommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDto findById(long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
        return new ProductDto(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDto> findAll(String name, Pageable pageable) {
        Page<Product> result = productRepository.searchByName(name, pageable);
        return result.map(ProductDto::new);
    }

    @Transactional
    public ProductDto save(ProductDto productDto) {
        Product entity = new Product();
        copyDtoToEntity(entity,productDto);
        entity = productRepository.save(entity);
        return new ProductDto(entity);
    }

    @Transactional
    public ProductDto update(long id, ProductDto productDto) {
        try{
            Product entity = productRepository.findById(id).get();
            copyDtoToEntity(entity,productDto);
            entity = productRepository.save(entity);
            return new ProductDto(entity);
        } catch (RuntimeException e) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }
    }

    public void copyDtoToEntity(Product entity, ProductDto dto) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }
        try{
            productRepository.deleteById(id);
        }catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }

    }


}
