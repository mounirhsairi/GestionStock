package com.example.gestiondestock.DTO.auth;





import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
	private String name ;
	private String username ;
	private String email ;
	private String password ;
	private String roleName;

    private String entrepriseName;
	private String  entrepriseDescription ;
	private String  entrepriseCodeFiscal ;
	private String  entrepriseEmail ;
	private String  entrepriseTel ;



}