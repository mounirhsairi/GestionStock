package com.example.gestiondestock.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.example.gestiondestock.DTO.MvnStockDto;

public class MvnStockValidator {
	public static List<String> validate(MvnStockDto mvnStockDto){
		List<String> errors = new ArrayList<>();
		if (mvnStockDto == null) {
			errors.add("Veuillez renseigner la date du mouvement");
			errors.add("Veuillez renseigner la quantite ");
			errors.add("Veuillez renseigner l'article");
			errors.add("Veuillez renseigner le type de mouvement");

			return errors ;

		}
		if(mvnStockDto.getDateMvt()== null)
		{
			errors.add("Veuillez renseigner la date du mouvement");
		}
		if(mvnStockDto.getQuantite()==null || mvnStockDto.getQuantite().compareTo(BigDecimal.ZERO)==0)
		{
			errors.add("Veuillez renseigner la quantite ");
		}
		if(mvnStockDto.getArticle()==null || mvnStockDto.getArticle().getId()==null)
		{
			errors.add("Veuillez renseigner l'article");
		}
		if(!StringUtils.hasLength(mvnStockDto.getTypeMvt().name()))
		{
			errors.add("Veuillez renseigner le type de mouvement");
		}
		return errors ;
	}
}

