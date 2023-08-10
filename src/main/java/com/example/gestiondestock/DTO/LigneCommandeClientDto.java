package com.example.gestiondestock.DTO;




import java.math.BigDecimal;

import com.example.gestiondestock.model.LigneCommandeClient;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LigneCommandeClientDto {
	private ArticleDto article ;
	private Integer id ;
	@JsonIgnore
	private CommandeClientDto commandeClient ;
	
	private BigDecimal quantite ;
	
	private BigDecimal prixUnitaire ;

	public static LigneCommandeClient toEntity(LigneCommandeClientDto ligneCommandeClientDto) {
	    LigneCommandeClient ligneCommandeClient = new LigneCommandeClient();
	    ligneCommandeClient.setId(ligneCommandeClientDto.getId());
	    ligneCommandeClient.setArticle(ArticleDto.toEntity(ligneCommandeClientDto.getArticle()));
	    ligneCommandeClient.setCommandeClient(CommandeClientDto.toEntity(ligneCommandeClientDto.getCommandeClient()));
	    ligneCommandeClient.setQuantite(ligneCommandeClientDto.getQuantite());
	    ligneCommandeClient.setPrixUnitaire(ligneCommandeClientDto.getPrixUnitaire());
	    return ligneCommandeClient;
	}

	public static LigneCommandeClientDto fromEntity(LigneCommandeClient ligneCommandeClient) {
	    if (ligneCommandeClient == null) {
	        return null;
	    }
	    
	    return LigneCommandeClientDto.builder()
	            .article(ArticleDto.fromEntity(ligneCommandeClient.getArticle()))
	            .id(ligneCommandeClient.getId())
	            .quantite(ligneCommandeClient.getQuantite())
	            .prixUnitaire(ligneCommandeClient.getPrixUnitaire())
	            .commandeClient(CommandeClientDto.fromEntity(ligneCommandeClient.getCommandeClient()))
	            .build();
	}

}
