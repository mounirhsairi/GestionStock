package com.example.gestiondestock.controller.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.gestiondestock.DTO.ArticleDto;
import com.example.gestiondestock.DTO.LigneVenteDto;
import com.example.gestiondestock.DTO.VentesDto;
import com.example.gestiondestock.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(Constants.APP_ROOT+"/ventes")
public interface VentesApi {

	@PostMapping(value = Constants.APP_ROOT + "/ventes/Create", consumes = "application/json", produces = "application/json")
	@ApiOperation(value="enregistrer un article",notes="cette methode permet d'enregistrer ou modifier une vente", response=VentesDto.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "vente créé/modifié"),
	        @ApiResponse(code = 400, message = "vente n'estpas valide")

	})
	ResponseEntity<VentesDto> save(@RequestBody VentesDto dto);
	
	@GetMapping(value = Constants.APP_ROOT + "/ventes/{idVentes}")
	ResponseEntity<VentesDto> findById(@PathVariable("idVentes")Integer idVentes);
	
	@GetMapping(value = Constants.APP_ROOT + "/ventes/all")
	ResponseEntity<List<VentesDto>> findAll();
	
	@GetMapping(value = Constants.APP_ROOT + "/ventes/lignesVentes/{idVentes}")
	ResponseEntity<List<LigneVenteDto>>findAllLigneVenteByVentesId(@PathVariable("idVentes")Integer idVentes);
	
	@GetMapping(value = Constants.APP_ROOT + "/ventes/lignesVentes/article/{idArticle}")
	ResponseEntity<List<LigneVenteDto>>findAllLigneVenteByArticleId(@PathVariable("idArticle")Integer idArticle);

	@DeleteMapping(value = Constants.APP_ROOT + "/ventes/delete/{idVentes}")
	ResponseEntity delete(@PathVariable("idVentes")Integer idVentes);
}
