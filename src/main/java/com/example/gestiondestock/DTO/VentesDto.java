package com.example.gestiondestock.DTO;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.example.gestiondestock.model.Ventes;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VentesDto {
	private Integer id ;
	private String code ;
	private Instant dateVente ;
	private String commentaire ;
	//@JsonIgnore
	private List<LigneVenteDto>ligneVente ;
	public static VentesDto fromEntity(Ventes ventes) {
	    if (ventes == null) {
	        return null;
	    }
	    
	    return VentesDto.builder()
	            .id(ventes.getId())
	            .code(ventes.getCode())
	            .dateVente(ventes.getDateVente())
	            .commentaire(ventes.getCommentaire())
	            
	            .build();
	}

	public static Ventes toEntity(VentesDto dto) {
	    if (dto == null) {
	        return null;
	    }
	    
	    Ventes ventes = new Ventes();
	    ventes.setId(dto.getId());
	    ventes.setCode(dto.getCode());
	    ventes.setDateVente(dto.getDateVente());
	    ventes.setCommentaire(dto.getCommentaire());
	    
	    
	    return ventes;
	}

	
	
}
