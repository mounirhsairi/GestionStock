package com.example.gestiondestock.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.example.gestiondestock.DTO.AdresseDto;

public class AdresseValidator {

	public static List<String> validate(AdresseDto adresseDto){
		List<String> errors = new ArrayList<>();
		if(adresseDto == null )
		{
			errors.add("Veuillez renseigner l'adresse 1");
			errors.add("Veuillez renseigner la ville ");
			errors.add("Veuillez renseigner le pays ");
			errors.add("Veuillez renseigner le code postal");


			return errors ;

		}
		if(!StringUtils.hasLength(adresseDto.getAdresse1()))
		{
			errors.add("Veuillez renseigner l'adresse 1");
			return errors ;

		}
		if(!StringUtils.hasLength(adresseDto.getVille()))
		{
			errors.add("Veuillez renseigner la ville ");
			return errors ;

		}
		if(!StringUtils.hasLength(adresseDto.getPays()))
		{
			errors.add("Veuillez renseigner le pays ");
			return errors ;

		}
		if(!StringUtils.hasLength(adresseDto.getCodePostale()))
		{
			errors.add("Veuillez renseigner le code postal");
			return errors ;

		}
		return errors ;
	}
	
}
