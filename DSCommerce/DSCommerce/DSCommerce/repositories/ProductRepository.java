package com.techverse.DSCommerce.repositories;

import com.techverse.DSCommerce.dtos.ProductDto;
import com.techverse.DSCommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


}
