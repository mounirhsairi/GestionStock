package com.example.gestiondestock.DTO;

import java.time.Instant;
import java.util.List;

import com.example.gestiondestock.model.FactureRetour;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class FactureRetourDto {
    private String code;
    private Instant dateFactureRetour;
    private CommandeClientDto commandeClient;
	//private String lastModifiedBy ;

    @JsonIgnore
    private List<LigneFactureRetourDto> ligneFactureRetour;

public static FactureRetourDto fromEntity(FactureRetour factureRetour) {
	if(factureRetour== null) {
		return null ;
	}
	return FactureRetourDto.builder()
			.code(factureRetour.getCode())
			.dateFactureRetour(factureRetour.getDateFactureRetour())
			.commandeClient(CommandeClientDto.fromEntity(factureRetour.getCommandeClient()))
			//.lastModifiedBy(factureRetour.getLastModifiedBy())
			.build();
}
public static FactureRetour toEntity(FactureRetourDto factureRetourDto) {
	if(factureRetourDto== null) {
		return null ;
	}
	FactureRetour factureRetour = new FactureRetour();
	factureRetour.setCode(factureRetourDto.getCode());
	factureRetour.setDateFactureRetour(factureRetourDto.getDateFactureRetour());
	factureRetour.setCommandeClient(CommandeClientDto.toEntity(factureRetourDto.getCommandeClient()));
	//factureRetour.setLastModifiedBy(factureRetourDto.getLastModifiedBy());
	return factureRetour;
}}
