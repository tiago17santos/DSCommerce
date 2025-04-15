package com.techverse.DSCommerce.controllers;


import com.techverse.DSCommerce.dtos.ProductDto;
import com.techverse.DSCommerce.dtos.ProductMinDto;
import com.techverse.DSCommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable long id) {
        ProductDto productDto = productService.findById(id);
        if (productDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDto);
    }

    @GetMapping
    public ResponseEntity<Page<ProductMinDto>> getAllProducts(Pageable pageable,
                                                                  @RequestParam(name = "name", defaultValue = "") String name) {
        Page<ProductMinDto> productDtos = productService.findAll(name, pageable);
        return ResponseEntity.ok(productDtos);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        productDto = productService.save(productDto);
        if (productDto == null) {
            return ResponseEntity.notFound().build();
        }
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(productDto.getId()).toUri();
        return ResponseEntity.created(uri).body(productDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable long id, @Valid @RequestBody ProductDto productDto) {
        productDto = productService.update(id, productDto);
        if (productDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        productService.delete(id);

        return ResponseEntity.noContent().build();
    }



}
