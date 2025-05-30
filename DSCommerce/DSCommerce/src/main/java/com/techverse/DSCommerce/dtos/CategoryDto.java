package com.techverse.DSCommerce.dtos;

import com.techverse.DSCommerce.entities.Category;

public class CategoryDto {

	private Long id;
	private String name;

	public CategoryDto() {
	}

	public CategoryDto(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public CategoryDto(Category entity) {
		id = entity.getId();
		name = entity.getName();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
