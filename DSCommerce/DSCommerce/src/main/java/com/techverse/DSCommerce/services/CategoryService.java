package com.techverse.DSCommerce.services;

import com.techverse.DSCommerce.dtos.CategoryDto;
import com.techverse.DSCommerce.entities.Category;
import com.techverse.DSCommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDto> findAll() {
        List<Category> result = repository.findAll();
        return result.stream().map(x -> new CategoryDto(x)).toList();
    }
}
