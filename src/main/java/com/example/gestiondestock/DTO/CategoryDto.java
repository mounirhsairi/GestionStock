package com.example.gestiondestock.DTO;

import java.util.List;
import java.util.ArrayList;

import com.example.gestiondestock.model.Article;
import com.example.gestiondestock.model.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Builder;
import lombok.Data;



import lombok.Builder;
import lombok.Data;
@Data
@Builder

public class CategoryDto {
	private Integer id ;
	private String code ;
	private String designation;
    private String name;
    private String lastModifiedBy ;

    @JsonIgnore
	private List<ArticleDto> articles ;
	public static CategoryDto fromEntity(Category category) {
		if(category == null) {
			return null ;
		}
		 return CategoryDto.builder()
	                .id(category.getId())
	                .code(category.getCode())
	                .designation(category.getDesignation())
	                .name(category.getName())
	                .lastModifiedBy(category.getLastModifiedBy())
	                .build();
	        
	        
	}
	public static Category toEntity(CategoryDto categoryDto) {
		if(categoryDto == null) {
			return null ;
		}
		Category category = new Category();
        category.setId(categoryDto.getId());
        category.setCode(categoryDto.getCode());
        category.setDesignation(categoryDto.getDesignation());
        category.setName(categoryDto.getName());
        category.setLastModifiedBy(categoryDto.getLastModifiedBy());
        
        return category;
		
	}}
