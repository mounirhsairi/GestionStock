package com.example.gestiondestock.DTO;

import java.math.BigDecimal;

import com.example.gestiondestock.model.Article;
import com.example.gestiondestock.model.FactureRetour;
import com.example.gestiondestock.model.LigneFactureRetour;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class LigneFactureRetourDto {
    private Integer id;

	private Article article;
    private FactureRetour factureRetour;
    private BigDecimal quantite;
    private BigDecimal prixUnitaire;
    
    public static LigneFactureRetourDto fromEntity(LigneFactureRetour ligneFactureRetour) {
    	if(ligneFactureRetour== null) {
    		return null ;
    	}
    	return LigneFactureRetourDto.builder()
    			.article(ligneFactureRetour.getArticle())
    			.factureRetour(ligneFactureRetour.getFactureRetour())
    			.id(ligneFactureRetour.getId())
    			.prixUnitaire(ligneFactureRetour.getPrixUnitaire())
    			.quantite(ligneFactureRetour.getQuantite())
    			.build();
    }
    public static LigneFactureRetour toEntity(LigneFactureRetourDto ligneFactureRetourDto) {
    	if(ligneFactureRetourDto== null) {
    		return null ;
    	}
    	LigneFactureRetour ligneFactureRetour = new LigneFactureRetour();
    	ligneFactureRetour.setArticle(ligneFactureRetourDto.getArticle());
    	ligneFactureRetour.setFactureRetour(ligneFactureRetourDto.getFactureRetour());
    	ligneFactureRetour.setId(ligneFactureRetourDto.getId());
    	ligneFactureRetour.setQuantite(ligneFactureRetourDto.getQuantite());
    	ligneFactureRetour.setPrixUnitaire(ligneFactureRetourDto.getPrixUnitaire());
    	return ligneFactureRetour;
    }}
