package com.example.gestiondestock.DTO;

import java.util.List;


import com.example.gestiondestock.model.Fournisseur;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FournisseurDto {
	private String  nom ;
	private Integer id ;
	private String  prenom ;
	
	private AdresseDto adresse ;
	
	private String photo ;
	
	private String  mail ;
	
	private String Numtel  ;
	@JsonIgnore
	private List<CommandeFournisseurDto> commandefournisseurs ;

	public static Fournisseur toEntity(FournisseurDto dto) {
	    if (dto == null) {
	        return null;
	    }
	    
	    Fournisseur fournisseur = new Fournisseur();
	    fournisseur.setNom(dto.getNom());
	    fournisseur.setId(dto.getId());
	    fournisseur.setPrenom(dto.getPrenom());
	    fournisseur.setAdresse(AdresseDto.toEntity(dto.getAdresse()));
	    fournisseur.setPhoto(dto.getPhoto());
	    fournisseur.setMail(dto.getMail());
	    fournisseur.setNumtel(dto.getNumtel());
	    
	    return fournisseur;
	}

	public static FournisseurDto fromEntity(Fournisseur fournisseur) {
	    if (fournisseur == null) {
	        return null;
	    }
	    
	    return FournisseurDto.builder()
	            .nom(fournisseur.getNom())
	            .id(fournisseur.getId())
	            .prenom(fournisseur.getPrenom())
	            .adresse(AdresseDto.fromEntity(fournisseur.getAdresse()))
	            .photo(fournisseur.getPhoto())
	            .mail(fournisseur.getMail())
	            .Numtel(fournisseur.getNumtel())
	            

	            .build();
	}
}
