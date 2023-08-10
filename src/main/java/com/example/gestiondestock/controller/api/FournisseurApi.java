package com.example.gestiondestock.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.gestiondestock.DTO.FournisseurDto;
import com.example.gestiondestock.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(Constants.APP_ROOT+"/fournisseurs")
public interface FournisseurApi {
	@PostMapping(value = Constants.APP_ROOT + "/fournisseurs/Create", consumes = "application/json", produces = "application/json")
	@ApiOperation(value="enregistrer un fournisseur",notes="cette methode permet d'enregistrer ou modifier un fournisseur", response=FournisseurDto.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "L'objet fournisseur créé/modifié"),
	        @ApiResponse(code = 400, message = "L'objet fournisseur n'estpas valide"),

	})
	FournisseurDto save(@RequestBody FournisseurDto dto);
	@GetMapping(value = Constants.APP_ROOT + "/fournisseurs/{idFournisseur}",produces = "application/json")
	@ApiOperation(value="rechercher un fournisseur par son id",notes="cette methode permet de rechercher un fournisseur", response=FournisseurDto.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "le fournisseur a ete trouve dans la BDD"),
	        @ApiResponse(code = 404, message = "le fournisseur n'existe pas")

	})
	FournisseurDto findById(@PathVariable("idFournisseur") Integer id);
	
	
	@GetMapping(value = Constants.APP_ROOT + "/fournisseurs/all",produces = "application/json")
	@ApiOperation(value="renvoie la liste des fournisseurs ",notes="cette methode permet de renvoyer la liste des fournisseurs ", responseContainer="list<FournisseurDto>")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "FournisseurDto.class")

	})
	List<FournisseurDto> findAll();
	@DeleteMapping(value = Constants.APP_ROOT + "/fournisseurs/delete/{idFournisseur}")
	@ApiOperation(value="supprimer un fournisseur ",notes="cette methode permet de supprimer un fournisseur", response=FournisseurDto.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Le fournisseur a ete supprimer"),

	})
	void delete(@PathVariable("idFournisseur")Integer id);

}
