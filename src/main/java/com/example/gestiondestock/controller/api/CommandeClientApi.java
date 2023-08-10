package com.example.gestiondestock.controller.api;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.gestiondestock.DTO.CommandeClientDto;
import com.example.gestiondestock.DTO.LigneCommandeClientDto;
import com.example.gestiondestock.model.EtatCommande;
import com.example.gestiondestock.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(Constants.APP_ROOT+"/commandesclients")

public interface CommandeClientApi {
	@PostMapping(value = Constants.APP_ROOT + "/commandesclients/Create")
	@ApiOperation(value="enregistrer une commande client",notes="cette methode permet d'enregistrer ou modifier une commande client", response=CommandeClientDto.class)

	ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto dto);
	@GetMapping(value = Constants.APP_ROOT + "/commandesclients/{idcommandeclient}")

	ResponseEntity<CommandeClientDto> findById(@PathVariable("idcommandeclient") Integer id);
	
	
	@GetMapping(value = Constants.APP_ROOT + "/commandesclients/all")
    ResponseEntity<List<CommandeClientDto>> findAll();
	@GetMapping(value = Constants.APP_ROOT + "/commandesclients/LignesCommandes/{idcommandeclient}")
    ResponseEntity<List<LigneCommandeClientDto>> findAllByCommandeClientId(@PathVariable Integer idcommandeclient);
	@PatchMapping(value = Constants.APP_ROOT + "/commandesclients/update/etat/{idCommande}/{etatCommande}")
	ResponseEntity<CommandeClientDto>updateEtatCommande(@PathVariable("idCommande")Integer idCommande,@PathVariable("etatCommande") EtatCommande etatCommande);
	@PatchMapping(value = Constants.APP_ROOT + "/commandesclients/update/quantite/{idCommande}/{idLigneCommande}/{quantite}")
	ResponseEntity<CommandeClientDto> updateQuantiteCommande(@PathVariable("idCommande")Integer idCommande,@PathVariable("idLigneCommande") Integer idLigneCommande,@PathVariable("quantite")BigDecimal quantite);
	
	@PatchMapping(value = Constants.APP_ROOT + "/commandesclients/update/prixUnitaire/{idCommande}/{idLigneCommande}/{prixUnitaire}")
	ResponseEntity<CommandeClientDto> updatePrixUnitaire(@PathVariable("idCommande")Integer idCommande,@PathVariable("idLigneCommande") Integer idLigneCommande,@PathVariable("prixUnitaire")BigDecimal prixUnitaire);

	@PatchMapping(value = Constants.APP_ROOT + "/commandesclients/update/client/{idCommande}/{idClient}")
	ResponseEntity<CommandeClientDto> updateClient(@PathVariable("idCommande")Integer idCommande,@PathVariable("idClient") Integer idClient);
	@PatchMapping(value = Constants.APP_ROOT + "/commandesclients/update/article/{idCommande}/{idLigneCommande}/{idArticle}")
	ResponseEntity<CommandeClientDto> updateArticle(@PathVariable("idCommande")Integer idCommande,@PathVariable("idLigneCommande") Integer idLigneCommande,@PathVariable("idArticle")Integer idArticle);
	@DeleteMapping(value = Constants.APP_ROOT + "/commandesclients/delete/article/{idCommande}/{idLigneCommande}")
	ResponseEntity<CommandeClientDto> deleteArticle(@PathVariable("idCommande")Integer idCommande,@PathVariable("idLigneCommande") Integer idLigneCommande);
	@DeleteMapping(value = Constants.APP_ROOT + "/commandesclients/delete/{idcommandeclient}")
	ResponseEntity delete(@PathVariable("idcommandeclient")Integer id);

}
