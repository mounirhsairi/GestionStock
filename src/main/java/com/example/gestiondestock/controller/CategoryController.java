package com.example.gestiondestock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestiondestock.DTO.CategoryDto;
import com.example.gestiondestock.Service.CategoryService;
import com.example.gestiondestock.controller.api.CategoryApi;
@RestController
public class CategoryController implements CategoryApi {
 
	CategoryService categoryService;
	@Autowired
	public CategoryController(CategoryService categoryRepository) {

		this.categoryService = categoryRepository;
	}

	@Override
	public CategoryDto save(CategoryDto dto) {
		// TODO Auto-generated method stub
		return categoryService.save(dto);
	}

	@Override
	public CategoryDto findById(Integer id) {
		// TODO Auto-generated method stub
		return categoryService.findById(id);
	}

	@Override
	public Page<CategoryDto> findAll(int page,int size) {
		 Pageable pageable = PageRequest.of(page, size);
	        Page<CategoryDto> categories = categoryService.findAll(pageable);

	        return categories;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		categoryService.delete(id);
	}

	@Override
	public List<CategoryDto> findAllByCodeCategory(String codeCategory) {
		// TODO Auto-generated method stub
		return categoryService.findAllByCodeCategory(codeCategory);
	}

	@Override
	public CategoryDto updateCategory(Integer categoryId, CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		return categoryService.updateCategory(categoryId, categoryDto);
	}

}
