package com.example.gestiondestock.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.gestiondestock.DTO.ArticleDto;
import com.example.gestiondestock.DTO.UtilisateurDto;
import com.example.gestiondestock.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(Constants.APP_ROOT+"/utilsateurs")
public interface UtilisateurApi {

	@PostMapping(value = Constants.APP_ROOT + "/utilsateurs/Create", consumes = "application/json", produces = "application/json")
	@ApiOperation(value="enregistrer un utilisateur",notes="cette methode permet d'enregistrer ou modifier un utilisateur", response=UtilisateurDto.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "L'objet utilisateur créé/modifié"),
	        @ApiResponse(code = 400, message = "L'objet utilisateur n'estpas valide")

	})
	UtilisateurDto save(@RequestBody UtilisateurDto dto);
	@GetMapping(value = Constants.APP_ROOT + "/utilsateurs/{idUtilisateur}",produces = "application/json")
	@ApiOperation(value="rechercher un utilisateur par son id",notes="cette methode permet de rechercher un utilisateur", response=UtilisateurDto.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "L'utilisateur a ete trouve dans la BDD"),
	        @ApiResponse(code = 404, message = "L'utilisateur n'existe pas")

	})
	UtilisateurDto findById(@PathVariable("idUtilisateur") Integer id);
	
	
	@GetMapping(value = Constants.APP_ROOT + "/utilsateurs/all",produces = "application/json")
	@ApiOperation(value="renvoie la liste des utilisateur ",notes="cette methode permet de renvoyer la liste des utilisateurs ", responseContainer="list<UtilisateurDto>")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "La liste des utilisateurs / une liste vide"),

	})
	List<UtilisateurDto> findAll();
	@DeleteMapping(value = Constants.APP_ROOT + "/utilsateurs/delete/{idUtilisateur}")
	@ApiOperation(value="supprimer un utilisateur ",notes="cette methode permet de supprimer un utilisateur ", response=UtilisateurDto.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "L'utilisateur a ete supprimer"),

	})
	void delete(@PathVariable("idUtilisateur")Integer id);
	@GetMapping(value = Constants.APP_ROOT + "/utilsateurs/filter/{idEntreprise}",produces = "application/json")

	List<UtilisateurDto> findAllByEntrepriseId(@PathVariable("idEntreprise")Integer idEntreprise) ;

	

}


