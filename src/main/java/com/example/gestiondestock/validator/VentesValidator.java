package com.example.gestiondestock.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.example.gestiondestock.DTO.VentesDto;

public class VentesValidator {

	public static List<String> validate(VentesDto dto) {
		List<String> errors = new ArrayList<>();
		if(dto == null) {

			errors.add("Veuillez renseigner le code de vente");
			errors.add("Veuillez renseigner la date  de vente");
			
			return errors;

		}
		if(!StringUtils.hasLength(dto.getCode()))
		{
			errors.add("Veuillez renseigner le code de vente");

		}
		if(dto.getDateVente()== null)
		{
			errors.add("Veuillez renseigner la date  de vente");

		}
		
		return errors;

		
		}

}
