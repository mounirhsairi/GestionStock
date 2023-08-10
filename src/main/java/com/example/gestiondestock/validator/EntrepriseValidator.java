package com.example.gestiondestock.validator;

import java.util.ArrayList;

import java.util.List;

import org.springframework.util.StringUtils;

import com.example.gestiondestock.DTO.EntrepriseDto;

public class EntrepriseValidator {

	public static List<String> validate(EntrepriseDto entreprisedto) {
		// TODO Auto-generated method stub
		List<String> errors = new ArrayList<>();
		if(entreprisedto == null) {
			
			errors.add("Veuillez renseigner le nom de l'entreprise");
			errors.add("Veuillez renseigner la description");
			errors.add("Veuillez renseigner le code fiscal de l'entreprise");
			errors.add("Veuillez renseigner l'email de l'entreprise");
			errors.add("Veuillez renseigner le numéro de  l'entreprise");

			
			return errors;

		}
		
		if(!StringUtils.hasLength(entreprisedto.getNom()))
		{
			errors.add("Veuillez renseigner le nom de l'entreprise");
		}
		if(!StringUtils.hasLength(entreprisedto.getDescription()))
		{
			errors.add("Veuillez renseigner la description");
		}
		if(!StringUtils.hasLength(entreprisedto.getCodeFiscal()))
		{
			errors.add("Veuillez renseigner le code fiscal de l'entreprise");
		}
		if(!StringUtils.hasLength(entreprisedto.getEmail()))
		{
			errors.add("Veuillez renseigner l'email de l'entreprise");
		}
		if(!StringUtils.hasLength(entreprisedto.getNumtel()))
		{
			errors.add("Veuillez renseigner le numéro de  l'entreprise");
		}
		
		
		
		errors.addAll(AdresseValidator.validate(entreprisedto.getAdresse()));
		return errors;
	}

}
