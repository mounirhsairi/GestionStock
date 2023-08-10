package com.example.gestiondestock.validator;

import java.util.List;

import org.springframework.util.StringUtils;

import com.example.gestiondestock.DTO.CategoryDto;

import java.util.ArrayList;

public class CategoryValidator {
	public static List<String> validate(CategoryDto categoryDTo){
		List<String> errors = new ArrayList<>();
		if(categoryDTo == null ||!StringUtils.hasLength(categoryDTo.getCode()))
		{
			errors.add("Veuillez renseigner le code de la categorie");
			return errors ;

		}
		return errors ;
	}

}
