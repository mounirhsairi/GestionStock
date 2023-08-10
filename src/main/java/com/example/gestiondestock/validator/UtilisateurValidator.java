package com.example.gestiondestock.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.example.gestiondestock.DTO.UtilisateurDto;


public class UtilisateurValidator {
	public static List<String> validate(UtilisateurDto utilisateurDTo){
		List<String> errors = new ArrayList<>();
		if(utilisateurDTo ==null) {
			errors.add("Veuillez renseigner le nom de l'utilisateur");
			errors.add("Veuillez renseigner le usenrame de l'utilisateur");
			errors.add("Veuillez renseigner le mot de passe de l'utilisateur");
			errors.add("Veuillez renseigner l'adresse de l'utilisateur");
			errors.addAll(AdresseValidator.validate(null));

			return errors;

		}
		if(!StringUtils.hasLength(utilisateurDTo.getNom()))
		{
			errors.add("Veuillez renseigner le nom de l'utilisateur");
		}
		if(!StringUtils.hasLength(utilisateurDTo.getUsername()))
		{
			errors.add("Veuillez renseigner le username de l'utilisateur");
		}
		if(!StringUtils.hasLength(utilisateurDTo.getEmail()))
		{
			errors.add("Veuillez renseigner l'email de l'utilisateur");
		}
		if(!StringUtils.hasLength(utilisateurDTo.getMdp()))
		{
			errors.add("Veuillez renseigner le mot de passe de l'utilisateur");
		}
		if(utilisateurDTo.getDatedenaissance()== null)
		{
			errors.add("Veuillez renseigner la date de naissance de l'utilisateur");
		}
		if(utilisateurDTo.getAdresse()== null)
		{
			errors.add("Veuillez renseigner l'adresse de l'utilisateur");
		}
		
		errors.addAll(AdresseValidator.validate(utilisateurDTo.getAdresse()));

		return errors;
		
	}

}
