package com.example.gestiondestock.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.example.gestiondestock.DTO.FournisseurDto;

public class FournisseurValidator {
	public static List<String> validate(FournisseurDto fournisseurDTo){
		List<String> errors = new ArrayList<>();
		if(fournisseurDTo == null) {
			errors.add("Veuillez renseigner le nom du fournisseur");
			errors.add("Veuillez renseigner le prénom du fournisseur");
			errors.add("Veuillez renseigner l'email du fournisseur");
			errors.add("Veuillez renseigner le numéro du fournisseur");
			return errors;

		}
	if(!StringUtils.hasLength(fournisseurDTo.getNom()))
	{
		errors.add("Veuillez renseigner le nom du fournisseur");
	}
	if(!StringUtils.hasLength(fournisseurDTo.getPrenom()))
	{
		errors.add("Veuillez renseigner le prénom du fournisseur");
	}
	if(!StringUtils.hasLength(fournisseurDTo.getMail()))
	{
		errors.add("Veuillez renseigner l'email du fournisseur");
	}
	if(!StringUtils.hasLength(fournisseurDTo.getNumtel()))
	{
		errors.add("Veuillez renseigner le numéro du fournisseur");
	}
	errors.addAll(AdresseValidator.validate(fournisseurDTo.getAdresse()));
	return errors;
	}
}
