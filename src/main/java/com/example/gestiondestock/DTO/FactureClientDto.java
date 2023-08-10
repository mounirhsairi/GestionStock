package com.example.gestiondestock.DTO;

import java.time.Instant;
import java.util.List;

import com.example.gestiondestock.model.FactureClient;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class FactureClientDto {
	private Integer id ;
    private String code;
    private CommandeClientDto commandeClient;
	private Instant dateFacture ;
	 private String lastModifiedBy ;


    public static FactureClientDto fromEntity(FactureClient factureClient)
    {
		if(factureClient == null)
		{
    	return null;
		}
		return FactureClientDto.builder()
				.id(factureClient.getId())
				.code(factureClient.getCode())
				.commandeClient(CommandeClientDto.fromEntity(factureClient.getCommandeClient()))
				.dateFacture(factureClient.getDateFacture())
				.lastModifiedBy(factureClient.getLastModifiedBy())
				.build();
    }
    public static FactureClient toEntity(FactureClientDto factureClientDto)
    {
    	FactureClient factureClient =new FactureClient();
    	factureClient.setId(factureClientDto.getId());
    	factureClient.setCode(factureClientDto.getCode());
    	factureClient.setCommandeClient(CommandeClientDto.toEntity(factureClientDto.getCommandeClient()));
    	factureClient.setDateFacture(factureClientDto.getDateFacture());
    	factureClient.setLastModifiedBy(factureClientDto.getLastModifiedBy());
		return factureClient;
    	
    }


}
