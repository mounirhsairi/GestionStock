package com.example.gestiondestock.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.gestiondestock.DTO.FactureFournisseurDto;
import com.example.gestiondestock.model.ArticleDetails;
import com.example.gestiondestock.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(Constants.APP_ROOT+"/facturesfournisseurs")

public interface FactureFournisseurApi {
	   @PostMapping(value = Constants.APP_ROOT + "/facturesfournisseurs/Create")
	   @ApiOperation(value="enregistrer une facture fournisseur",notes="cette methode permet d'enregistrer ou modifier une facture fournisseur", response=FactureFournisseurDto.class)
        public FactureFournisseurDto createFactureFournisseur(@RequestBody FactureFournisseurDto factureFournisseurDto) ;
		@GetMapping(value = Constants.APP_ROOT + "/facturesfournisseurs/all",produces = "application/json")
		@ApiOperation(value="affiche la liste des factures fournisseurs",notes="cette methode permet d'afficher la liste des factures fournisseurs", response=FactureFournisseurDto.class)
	    public List<FactureFournisseurDto> getAllFactureFournisseurs() ;
		@GetMapping(value = Constants.APP_ROOT + "/facturesfournisseurs/{idFactureFournisseur}",produces = "application/json")
		@ApiOperation(value="affiche la liste des factures Fournisseurs",notes="cette methode permet d'afficher la liste des factures Fournisseurs", response=FactureFournisseurDto.class)
	    public FactureFournisseurDto getFactureFournisseurById(@PathVariable("idFactureFournisseur")Integer id) ;
		@PatchMapping(value = Constants.APP_ROOT + "/facturesfournisseurs/update/{idFactureFournisseur}", consumes = "application/json", produces = "application/json")
		@ApiOperation(value = "mettre à jour une facture Fournisseur", notes = "cette méthode permet de mettre à jour facture Fournisseur", response = FactureFournisseurDto.class)
		@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "La facturesfournisseurs a été mis à jour avec succès"),
		    @ApiResponse(code = 404, message = "Aucune facturesfournisseurs n'a été trouvé avec le code fourni")
		})
	    public FactureFournisseurDto updateFactureFournisseur(@PathVariable("idFactureFournisseur")Integer id,@RequestBody FactureFournisseurDto factureFournisseurDto) ;
		@DeleteMapping(value = Constants.APP_ROOT + "/facturesfournisseurs/delete/{id}")
		@ApiOperation(value="supprimer une facturesfournisseurs ",notes="cette methode permet de supprimer une facturesfournisseurs ", response=FactureFournisseurDto.class)
		public void deleteFactureFournisseur(@PathVariable Integer id) ;
	    @GetMapping(value = Constants.APP_ROOT +"/{id}/montantF")
	    public double calculateMontantFactureFournisseur(@PathVariable Integer id) ;
	    @GetMapping(value = Constants.APP_ROOT +"/facturesfournisseurs/{id}/articles")
	    public List<ArticleDetails> getArticlesAndPrixQuantite(@PathVariable Integer id);

}
