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

import com.example.gestiondestock.DTO.CommandeFournisseurDto;
import com.example.gestiondestock.DTO.LigneCommandeFournisseurDto;
import com.example.gestiondestock.model.EtatCommande;
import com.example.gestiondestock.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(Constants.APP_ROOT+"/commandesfournisseurs")

public interface CommandeFournisseurApi {
	@PostMapping(value = Constants.APP_ROOT + "/commandesfournisseurs/Create")
	@ApiOperation(value="enregistrer une commande fournisseur",notes="cette methode permet d'enregistrer ou modifier une commande fournisseur", response=CommandeFournisseurDto.class)

	CommandeFournisseurDto save(@RequestBody CommandeFournisseurDto dto);
	@GetMapping(value = Constants.APP_ROOT + "/commandesfournisseurs/{idcommandefournisseur}")

	CommandeFournisseurDto findById(@PathVariable("idcommandefournisseur") Integer id);
	
	
	@GetMapping(value = Constants.APP_ROOT + "/commandesfournisseurs/all")
	
    List<CommandeFournisseurDto> findAll();
	@GetMapping(value = Constants.APP_ROOT + "/commandesfournisseurs/LignesCommandes/{idcommandefournisseur}")
    ResponseEntity<List<LigneCommandeFournisseurDto>> findAllByCommandeFournisseurId(@PathVariable("idcommandefournisseur") Integer idcommandefournisseur);
	@PatchMapping(value = Constants.APP_ROOT + "/commandesfournisseurs/update/etat/{idCommande}/{etatCommande}")
	ResponseEntity<CommandeFournisseurDto>updateEtatCommande(@PathVariable("idCommande")Integer idCommande,@PathVariable("etatCommande") EtatCommande etatCommande);
	@PatchMapping(value = Constants.APP_ROOT + "/commandesfournisseurs/update/quantite/{idCommande}/{idLigneCommande}/{quantite}")
	ResponseEntity<CommandeFournisseurDto> updateQuantiteCommande(@PathVariable("idCommande")Integer idCommande,@PathVariable("idLigneCommande") Integer idLigneCommande,@PathVariable("quantite")BigDecimal quantite);
	
	@PatchMapping(value = Constants.APP_ROOT + "/commandesfournisseurs/update/prixUnitaire/{idCommande}/{idLigneCommande}/{prixUnitaire}")
	ResponseEntity<CommandeFournisseurDto> updatePrixUnitaire(@PathVariable("idCommande")Integer idCommande,@PathVariable("idLigneCommande") Integer idLigneCommande,@PathVariable("prixUnitaire")BigDecimal prixUnitaire);

	@PatchMapping(value = Constants.APP_ROOT + "/commandesfournisseurs/update/fournisseur/{idCommande}/{idFournisseur}")
	ResponseEntity<CommandeFournisseurDto> updateFournisseur(@PathVariable("idCommande")Integer idCommande,@PathVariable("idFournisseur") Integer idFournisseur);
	@PatchMapping(value = Constants.APP_ROOT + "/commandesfournisseurs/update/article/{idCommande}/{idLigneCommande}/{idArticle}")
	ResponseEntity<CommandeFournisseurDto> updateArticle(@PathVariable("idCommande")Integer idCommande,@PathVariable("idLigneCommande") Integer idLigneCommande,@PathVariable("idArticle")Integer idArticle);
	@DeleteMapping(value = Constants.APP_ROOT + "/commandesfournisseurs/delete/article/{idCommande}/{idLigneCommande}")
	ResponseEntity<CommandeFournisseurDto> deleteArticle(@PathVariable("idCommande")Integer idCommande,@PathVariable("idLigneCommande") Integer idLigneCommande);
	@DeleteMapping(value = Constants.APP_ROOT + "/commandesfournisseurs/delete/{idcommandefournisseur}")
	 void delete(@PathVariable("idcommandefournisseur")Integer id);
}
