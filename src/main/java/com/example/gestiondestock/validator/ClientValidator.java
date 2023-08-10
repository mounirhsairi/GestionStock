package com.example.gestiondestock.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.example.gestiondestock.DTO.ClientDto;

public class ClientValidator {
	public static List<String> validate(ClientDto clientDTo){
		List<String> errors = new ArrayList<>();
		if(clientDTo == null) {
			errors.add("Veuillez renseigner le nom du client");
			errors.add("Veuillez renseigner le prénom du client");
			errors.add("Veuillez renseigner l'email du client");
			errors.add("Veuillez renseigner le numéro du client");
			return errors;

		}
	if(!StringUtils.hasLength(clientDTo.getNom()))
	{
		errors.add("Veuillez renseigner le nom du client");
	}
	if(!StringUtils.hasLength(clientDTo.getPrenom()))
	{
		errors.add("Veuillez renseigner le prénom du client");
	}
	if(!StringUtils.hasLength(clientDTo.getMail()))
	{
		errors.add("Veuillez renseigner l'email du client");
	}
	if(!StringUtils.hasLength(clientDTo.getNumtel()))
	{
		errors.add("Veuillez renseigner le numéro du client");
	}
	errors.addAll(AdresseValidator.validate(clientDTo.getAdresse()));
	return errors;
	}
}
