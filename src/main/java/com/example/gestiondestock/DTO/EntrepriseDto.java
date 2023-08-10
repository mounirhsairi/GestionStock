package com.example.gestiondestock.DTO;

import java.util.List;




import com.example.gestiondestock.model.Entreprise;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EntrepriseDto {
	private String  nom ;
	private Integer id ;
	private String  description ;
	private AdresseDto adresse ;
	private String  codeFiscal ;
	
	private String  photo ;
	
	private String  email ;
	
	private String  numtel ;
	
	private String  siteWeb ;
	@JsonIgnore
	private List<UtilisateurDto> utilisateur ;
	public static EntrepriseDto fromEntity (Entreprise entreprise) {

		if (entreprise == null) {

		return null;

		}
		return EntrepriseDto.builder()
	            .nom(entreprise.getNom())
	            .id(entreprise.getId())
	            .description(entreprise.getDescription())
	    		.adresse (AdresseDto.fromEntity(entreprise.getAdresse()))
	            .codeFiscal(entreprise.getCodeFiscal())
	            .photo(entreprise.getPhoto())
	            .email(entreprise.getEmail())
	            .numtel(entreprise.getNumtel())
	            .siteWeb(entreprise.getSiteWeb())
	            
	            .build();
	}
	public static Entreprise toEntity(EntrepriseDto dto) {
	    if (dto == null) {
	        return null;
	    }
	    
	    Entreprise entreprise = new Entreprise();
	    entreprise.setNom(dto.getNom());
	    entreprise.setId(dto.getId());
	    entreprise.setDescription(dto.getDescription());
	    entreprise.setAdresse(AdresseDto.toEntity(dto.getAdresse()));
	    entreprise.setCodeFiscal(dto.getCodeFiscal());
	    entreprise.setPhoto(dto.getPhoto());
	    entreprise.setEmail(dto.getEmail());
	    entreprise.setNumtel(dto.getNumtel());
	    entreprise.setSiteWeb(dto.getSiteWeb());
	    
	  
	    

	    return entreprise;
	}


}
