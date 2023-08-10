package com.example.gestiondestock.DTO;


import java.math.BigDecimal;

import com.example.gestiondestock.model.LigneCommandeFournisseur;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LigneCommandeFournisseurDto {
	private ArticleDto article ;
	private Integer id ;
	@JsonIgnore
	private CommandeFournisseurDto commandeFournisseur ;
    private BigDecimal quantite ;
	
	private BigDecimal prixUnitaire ;
	public static LigneCommandeFournisseurDto fromEntity(LigneCommandeFournisseur ligneCommandeFournisseur) {
	    if (ligneCommandeFournisseur == null) {
	        return null;
	    }
	    
	    return LigneCommandeFournisseurDto.builder()
	            .article(ArticleDto.fromEntity(ligneCommandeFournisseur.getArticle()))
	            .id(ligneCommandeFournisseur.getId())
	            .commandeFournisseur(CommandeFournisseurDto.fromEntity(ligneCommandeFournisseur.getCommandeFournisseur()))
	            .quantite(ligneCommandeFournisseur.getQuantite())
	            .prixUnitaire(ligneCommandeFournisseur.getPrixUnitaire())

	            .build();
	}

	public static LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDto dto) {
	    if (dto == null) {
	        return null;
	    }
	    
	    LigneCommandeFournisseur ligneCommandeFournisseur = new LigneCommandeFournisseur();
	    ligneCommandeFournisseur.setArticle(ArticleDto.toEntity(dto.getArticle()));
	    ligneCommandeFournisseur.setId(dto.getId());
	    ligneCommandeFournisseur.setCommandeFournisseur(CommandeFournisseurDto.toEntity(dto.getCommandeFournisseur()));
	    ligneCommandeFournisseur.setQuantite(ligneCommandeFournisseur.getQuantite());
	    ligneCommandeFournisseur.setPrixUnitaire(ligneCommandeFournisseur.getPrixUnitaire());

	    return ligneCommandeFournisseur;
	}

}
