package com.example.gestiondestock.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.gestiondestock.DTO.CategoryDto;

public interface CategoryService {
	CategoryDto save(CategoryDto dto);
	CategoryDto findById(Integer id);
	List<CategoryDto> findAllByCodeCategory(String code);
	CategoryDto updateCategory(Integer idCategory, CategoryDto categoryDto);
	Page<CategoryDto> findAll(Pageable pageable);
	void delete(Integer id);
	
}
