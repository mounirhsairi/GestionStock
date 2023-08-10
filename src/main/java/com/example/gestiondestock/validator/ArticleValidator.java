 package com.example.gestiondestock.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.example.gestiondestock.DTO.ArticleDto;

public class ArticleValidator {
	public static List<String> validate(ArticleDto articleDTo){
		List<String> errors = new ArrayList<>();
		if(articleDTo == null) {
			errors.add("Veuillez renseigner le code de l'article");
			errors.add("Veuillez renseigner la designation de l'article");
			errors.add("Veuillez renseigner le prix unitaire hors tax de l'article");
			errors.add("Veuillez renseigner le taux TVA de l'article");
			errors.add("Veuillez renseigner le prix unitaire ttc de l'article");
			errors.add("Veuillez renseigner une categorie");
			return errors;

		}
			
		if(!StringUtils.hasLength(articleDTo.getCodeArticle()))
		{
			errors.add("Veuillez renseigner le code de l'article");
		}
		if(!StringUtils.hasLength(articleDTo.getDesignation()))
		{
			errors.add("Veuillez renseigner la designation de l'article");
		}
		if(articleDTo.getPrixUnitaireHt() == null)
		{
			errors.add("Veuillez renseigner le prix unitaire hors tax de l'article");
		}
		if(articleDTo.getTauxTva() == null)
		{
			errors.add("Veuillez renseigner le taux TVA de l'article");
		}
		if(articleDTo.getPrixUnitaireTtc() == null)
		{
			errors.add("Veuillez renseigner le prix unitaire ttc de l'article");
		}
		if(articleDTo.getCategory() == null)
		{
			errors.add("Veuillez renseigner une categorie");
		}
		
		
		
		return errors;
	}
}
