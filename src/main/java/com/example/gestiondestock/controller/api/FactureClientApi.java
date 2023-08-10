package com.example.gestiondestock.controller.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.gestiondestock.DTO.FactureClientDto;
import com.example.gestiondestock.model.ArticleDetails;
import com.example.gestiondestock.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(Constants.APP_ROOT+"/facturesclients")

public interface FactureClientApi {
	   @PostMapping(value = Constants.APP_ROOT + "/facturesclients/Create")
	   @ApiOperation(value="enregistrer une facture client",notes="cette methode permet d'enregistrer ou modifier une facture client", response=FactureClientDto.class)
        public FactureClientDto createFactureClient(@RequestBody FactureClientDto factureClientDto) ;
		@GetMapping(value = Constants.APP_ROOT + "/facturesclients/all",produces = "application/json")
		@ApiOperation(value="affiche la liste des factures clients",notes="cette methode permet d'afficher la liste des factures clients", response=FactureClientDto.class)
	    public List<FactureClientDto> getAllFactureClients() ;
		@GetMapping(value = Constants.APP_ROOT + "/facturesclients/{idFactureClient}",produces = "application/json")
		@ApiOperation(value="affiche la liste des factures clients",notes="cette methode permet d'afficher la liste des factures clients", response=FactureClientDto.class)
	    public FactureClientDto getFactureClientById(@PathVariable("idFactureClient")Integer id) ;
		@PatchMapping(value = Constants.APP_ROOT + "/facturesclients/update/{idFactureClient}", consumes = "application/json", produces = "application/json")
		@ApiOperation(value = "mettre à jour une facture Client", notes = "cette méthode permet de mettre à jour facture Client", response = FactureClientDto.class)
		@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "La facture Client a été mis à jour avec succès"),
		    @ApiResponse(code = 404, message = "Aucune facture Client n'a été trouvé avec le code fourni")
		})
	    public FactureClientDto updateFactureClient(@PathVariable("idFactureClient")Integer id,@RequestBody FactureClientDto factureClientDto) ;
		@DeleteMapping(value = Constants.APP_ROOT + "/facturesclients/delete/{id}")
		@ApiOperation(value="supprimer un article ",notes="cette methode permet de supprimer un article ", response=FactureClientDto.class)
		public void deleteFactureClient(@PathVariable Integer id) ;
	    @GetMapping(value=Constants.APP_ROOT +"/{id}/montant")
	    public double calculateMontantFactureClient(@PathVariable Integer id) ;
	    @GetMapping(value = Constants.APP_ROOT +"/factureclients/{id}/articles")
	    public List<ArticleDetails> getArticlesAndPrixQuantite(@PathVariable Integer id);

}
