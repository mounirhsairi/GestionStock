package com.example.gestiondestock.DTO;

import java.math.BigDecimal;

import com.example.gestiondestock.model.LigneVente;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class LigneVenteDto {
	private Integer id ;
	@JsonIgnore
	private VentesDto vente ;
	
	private BigDecimal quantite ;
	
	private BigDecimal prixUnitaire ;
	private ArticleDto article ;

	public static LigneVenteDto fromEntity(LigneVente ligneVente) {
	    if (ligneVente == null) {
	        return null;
	    }
	    
	    return LigneVenteDto.builder()
	            .id(ligneVente.getId())
	            .vente(VentesDto.fromEntity(ligneVente.getVente()))
	            .quantite(ligneVente.getQuantite())
	            .prixUnitaire(ligneVente.getPrixUnitaire())
	            .article(ArticleDto.fromEntity(ligneVente.getArticle()))
	            .build();
	}

	public static LigneVente toEntity(LigneVenteDto dto) {
	    if (dto == null) {
	        return null;
	    }
	    
	    LigneVente ligneVente = new LigneVente();
	    ligneVente.setId(dto.getId());
	    ligneVente.setVente(VentesDto.toEntity(dto.getVente()));
	    ligneVente.setQuantite(dto.getQuantite());
	    ligneVente.setPrixUnitaire(dto.getPrixUnitaire());
	    ligneVente.setArticle(ArticleDto.toEntity(dto.getArticle()));
	    
	    return ligneVente;
	}

}
