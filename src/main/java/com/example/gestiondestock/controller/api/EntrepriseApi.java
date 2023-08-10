package com.example.gestiondestock.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.gestiondestock.DTO.EntrepriseDto;
import com.example.gestiondestock.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(Constants.APP_ROOT+"/entreprises")
public interface EntrepriseApi {
	@PostMapping(value = Constants.APP_ROOT + "/entreprises/Create", consumes = "application/json", produces = "application/json")
	@ApiOperation(value="enregistrer une entreprise",notes="cette methode permet d'enregistrer ou modifier une entreprise", response=EntrepriseDto.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "L'objet entreprise créé/modifié"),
	        @ApiResponse(code = 400, message = "L'objet entreprise n'estpas valide")

	})
	EntrepriseDto save(@RequestBody EntrepriseDto dto);
	@GetMapping(value = Constants.APP_ROOT + "/entreprises/{idEntreprise}",produces = "application/json")
	@ApiOperation(value="rechercher une entreprise par son id",notes="cette methode permet de rechercher une entreprise", response=EntrepriseDto.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "L'entreprise a ete trouve dans la BDD"),
	        @ApiResponse(code = 404, message = "L'entreprise n'existe pas")

	})
	EntrepriseDto findById(@PathVariable("idEntreprise") Integer id);
	
	@GetMapping(value = Constants.APP_ROOT + "/entreprises/code/{codeEntreprise}", produces = "application/json")
	@ApiOperation(value="rechercher une entreprise par son code",notes="cette methode permet de rechercher une entreprises", response=EntrepriseDto.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "L'entreprise a ete trouve dans la BDD"),
	        @ApiResponse(code = 404, message = "aucune entreprise n'a ete touve avec le code fourni")

	})

	EntrepriseDto findByCodeEntreprise(@PathVariable("codeEntreprise")String code);
	@GetMapping(value = Constants.APP_ROOT + "/entreprises/all",produces = "application/json")
	@ApiOperation(value="renvoie la liste des entreprises ",notes="cette methode permet de renvoyer la liste des entreprises ", responseContainer="list<EntrepriseDto>")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "La liste des entreprises / une liste vide"),

	})
	List<EntrepriseDto> findAll();
	@DeleteMapping(value = Constants.APP_ROOT + "/entreprises/delete/{idEntreprise}")
	@ApiOperation(value="supprimer une entreprise ",notes="cette methode permet de supprimer une entreprise ", response=EntrepriseDto.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "L'entreprise a ete supprimer"),

	})
	void delete(@PathVariable("idEntreprise")Integer id);
}
