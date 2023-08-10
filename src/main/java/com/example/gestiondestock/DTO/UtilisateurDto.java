package com.example.gestiondestock.DTO;

import java.time.Instant;

import java.util.List;
import java.util.stream.Collectors;

import com.example.gestiondestock.model.User;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UtilisateurDto {
	private Integer id ;

	private String nom ;
	
	private String username ;
	
	private String email ;
	
	private Instant datedenaissance ;
	private String mdp ;
	private AdresseDto adresse ;
	private String photo ;
	private EntrepriseDto entreprise  ;
	private List<RolesDto> roles ;
	
	public static UtilisateurDto fromEntity(User utilisateur) {
	    if (utilisateur == null) {
	        return null;
	    }
	    
	    return UtilisateurDto.builder()
	            .id(utilisateur.getId())
	            .nom(utilisateur.getName())
	            .username(utilisateur.getUsername())
	            .email(utilisateur.getEmail())
	            .datedenaissance(utilisateur.getDatedenaissance())
	            .mdp(utilisateur.getPassword())
	            .adresse(AdresseDto.fromEntity(utilisateur.getAdresse()))
	            .photo(utilisateur.getPhoto())
	            .entreprise(EntrepriseDto.fromEntity(utilisateur.getEntreprise()))
	            .roles(utilisateur.getRoles().stream()
	                    .map(RolesDto::fromEntity)
	                    .collect(Collectors.toList()))
	            .build();
	}

	public static User toEntity(UtilisateurDto dto) {
	    if (dto == null) {
	        return null;
	    }
	    
	    User utilisateur = new User();
	    utilisateur.setId(dto.getId());
	    utilisateur.setName(dto.getNom());
	    utilisateur.setUsername(dto.getUsername());
	    utilisateur.setEmail(dto.getEmail());
	    utilisateur.setDatedenaissance(dto.getDatedenaissance());
	    utilisateur.setPassword(dto.getMdp());
	    utilisateur.setAdresse(AdresseDto.toEntity(dto.getAdresse()));
	    utilisateur.setPhoto(dto.getPhoto());
	    utilisateur.setEntreprise(EntrepriseDto.toEntity(dto.getEntreprise()));
	    utilisateur.setRoles(dto.getRoles().stream()
	            .map(RolesDto::toEntity)
	            .collect(Collectors.toList()));
	    
	    return utilisateur;
	}

}
