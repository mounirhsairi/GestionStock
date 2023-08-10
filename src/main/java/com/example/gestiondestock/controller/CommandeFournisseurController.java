package com.example.gestiondestock.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestiondestock.DTO.CommandeFournisseurDto;
import com.example.gestiondestock.DTO.LigneCommandeFournisseurDto;
import com.example.gestiondestock.Service.CommandeFournisseurService;
import com.example.gestiondestock.controller.api.CommandeFournisseurApi;
import com.example.gestiondestock.model.EtatCommande;
@RestController
public class CommandeFournisseurController implements CommandeFournisseurApi{

private CommandeFournisseurService commandeFournisseurService ;

public CommandeFournisseurController(CommandeFournisseurService commandeFournisseurService) {
	this.commandeFournisseurService = commandeFournisseurService;
}

@Override
public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
	// TODO Auto-generated method stub
	return commandeFournisseurService.save(dto) ;
}

@Override
public CommandeFournisseurDto findById(Integer id) {
	// TODO Auto-generated method stub
	return commandeFournisseurService.findById(id);
}


@Override
public List<CommandeFournisseurDto> findAll() {
	// TODO Auto-generated method stub
	return commandeFournisseurService.findAll();
}

@Override
public void delete(Integer id) {
	commandeFournisseurService.delete(id);
	
}

@Override
public ResponseEntity<List<LigneCommandeFournisseurDto>> findAllByCommandeFournisseurId(Integer idcommandefournisseur) {
	// TODO Auto-generated method stub
	return ResponseEntity.ok(commandeFournisseurService.findAllLignesCommandesFournisseurByCommandeFournisseurId(idcommandefournisseur));

}

@Override
public ResponseEntity<CommandeFournisseurDto> updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
	// TODO Auto-generated method stub
	return ResponseEntity.ok(commandeFournisseurService.updateEtatCommande(idCommande, etatCommande));
}

@Override
public ResponseEntity<CommandeFournisseurDto> updateQuantiteCommande(Integer idCommande, Integer idLigneCommande,
		BigDecimal quantite) {
	// TODO Auto-generated method stub
	return ResponseEntity.ok(commandeFournisseurService.updateQuantiteCommande(idCommande, idLigneCommande, quantite));
}

@Override
public ResponseEntity<CommandeFournisseurDto> updatePrixUnitaire(Integer idCommande, Integer idLigneCommande,
		BigDecimal prixUnitaire) {
	// TODO Auto-generated method stub
	return ResponseEntity.ok(commandeFournisseurService.updatePrixUnitaire(idCommande, idLigneCommande, prixUnitaire));
}

@Override
public ResponseEntity<CommandeFournisseurDto> updateFournisseur(Integer idCommande, Integer idFournisseur) {
	// TODO Auto-generated method stub
	return ResponseEntity.ok(commandeFournisseurService.updateFournisseur(idCommande, idFournisseur));
}

@Override
public ResponseEntity<CommandeFournisseurDto> updateArticle(Integer idCommande, Integer idLigneCommande,
		Integer idArticle) {
	// TODO Auto-generated method stub
	return ResponseEntity.ok(commandeFournisseurService.updateArticle(idCommande, idLigneCommande, idArticle));
}

@Override
public ResponseEntity<CommandeFournisseurDto> deleteArticle(Integer idCommande, Integer idLigneCommande) {
	// TODO Auto-generated method stub
	return ResponseEntity.ok(commandeFournisseurService.deleteArticle(idCommande, idLigneCommande));
}


}
