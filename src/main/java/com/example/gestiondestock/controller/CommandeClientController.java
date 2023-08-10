package com.example.gestiondestock.controller;

import java.math.BigDecimal;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestiondestock.DTO.CommandeClientDto;
import com.example.gestiondestock.DTO.LigneCommandeClientDto;
import com.example.gestiondestock.Service.CommandeClientService;
import com.example.gestiondestock.controller.api.CommandeClientApi;
import com.example.gestiondestock.model.EtatCommande;

@RestController

public class CommandeClientController implements CommandeClientApi{
	

	private CommandeClientService  commandeClientService ;
	@Autowired
	public CommandeClientController(CommandeClientService commandeClientService) {
		
		this.commandeClientService = commandeClientService;
	}

	@Override
	public ResponseEntity<CommandeClientDto> save(CommandeClientDto dto) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(commandeClientService.save(dto));
	}

	@Override
	public ResponseEntity<CommandeClientDto> findById(Integer id) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(commandeClientService.findById(id));
	}

	/*@Override
	public ResponseEntity<CommandeClientDto> findByCodeCommandeClient(String code) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(commandeClientService.findByCodeCommandeClient(code));
	}*/

	@Override
	public ResponseEntity<List<CommandeClientDto>> findAll() {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(commandeClientService.findAll());
	}

	@Override
	public ResponseEntity delete(Integer id) {
		// TODO Auto-generated method stub
		commandeClientService.delete( id);
		return ResponseEntity.ok().build();	}

	@Override
	public ResponseEntity<CommandeClientDto> updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(commandeClientService.updateEtatCommande(idCommande, etatCommande));
	}

	@Override
	public ResponseEntity<CommandeClientDto> updateQuantiteCommande(Integer idCommande, Integer idLigneCommande,
			BigDecimal quantite) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(commandeClientService.updateQuantiteCommande(idCommande, idLigneCommande, quantite));
	}

	@Override
	public ResponseEntity<CommandeClientDto> updateClient(Integer idCommande, Integer idClient) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(commandeClientService.updateClient(idCommande, idClient));
	}
	@Override
	public ResponseEntity<CommandeClientDto> updateArticle(Integer idCommande, Integer idLigneCommande,
			Integer idArticle) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(commandeClientService.updateArticle(idCommande, idLigneCommande ,idArticle));
	}

	@Override
	public ResponseEntity<CommandeClientDto> deleteArticle(Integer idCommande, Integer idLigneCommande) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(commandeClientService.deleteArticle(idCommande, idLigneCommande));
	}

	@Override
	public ResponseEntity<List<LigneCommandeClientDto>> findAllByCommandeClientId(Integer idcommandeclient) {
	    List<LigneCommandeClientDto> lignesCommandeClient = commandeClientService.findAllLignesCommandesClientByCommandeClientId(idcommandeclient);
	    return ResponseEntity.ok(lignesCommandeClient);
	}


	@Override
	public ResponseEntity<CommandeClientDto> updatePrixUnitaire(Integer idCommande, Integer idLigneCommande,
			BigDecimal prixUnitaire) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(commandeClientService.updatePrixUnitaire(idCommande, idLigneCommande, prixUnitaire));
	}

}
