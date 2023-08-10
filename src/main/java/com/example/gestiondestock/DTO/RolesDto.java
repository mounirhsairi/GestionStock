package com.example.gestiondestock.DTO;


import com.example.gestiondestock.model.Roles;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RolesDto {
	private String rolename;
	private Integer id ;
	private UtilisateurDto Utilisateur ;
	public static RolesDto fromEntity(Roles role) {
	    if (role == null) {
	        return null;
	    }
	    
	    return RolesDto.builder()
	            .rolename(role.getName())
	            .id(role.getId())
	            .Utilisateur(UtilisateurDto.fromEntity(role.getUtilisateur()))
	            .build();
	}

	public static Roles toEntity(RolesDto dto) {
	    if (dto == null) {
	        return null;
	    }
	    
	    Roles role = new Roles();
	    role.setName(dto.getRolename());
	    role.setId(dto.getId());
	    role.setUtilisateur(UtilisateurDto.toEntity(dto.getUtilisateur()));
	    
	    return role;
	}

}
