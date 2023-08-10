package com.example.gestiondestock.DTO;

import java.time.Instant;
import java.util.List;

import com.example.gestiondestock.model.FactureFournisseur;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class FactureFournisseurDto {
    private String code;
    private CommandeFournisseurDto commandeFournisseur;
	private Instant dateFacture ;
	 private String lastModifiedBy ;


    public static FactureFournisseurDto fromEntity(FactureFournisseur factureFournisseur)
    {
		if(factureFournisseur == null)
		{
    	return null;
		}
		return FactureFournisseurDto.builder()
				.code(factureFournisseur.getCode())
				.commandeFournisseur(CommandeFournisseurDto.fromEntity(factureFournisseur.getCommandeFournisseur()))
				.dateFacture(factureFournisseur.getDateFacture())
				.lastModifiedBy(factureFournisseur.getLastModifiedBy())
				.build();
    }
    public static FactureFournisseur toEntity(FactureFournisseurDto factureFournisseurDto)
    {
    	FactureFournisseur factureFournisseur =new FactureFournisseur();
    	factureFournisseur.setCode(factureFournisseurDto.getCode());
    	factureFournisseur.setCommandeFournisseur(CommandeFournisseurDto.toEntity(factureFournisseurDto.getCommandeFournisseur()));
    	factureFournisseur.setDateFacture(factureFournisseurDto.getDateFacture());
    	factureFournisseur.setLastModifiedBy(factureFournisseurDto.getLastModifiedBy());
		return factureFournisseur;
    	
    }


}
