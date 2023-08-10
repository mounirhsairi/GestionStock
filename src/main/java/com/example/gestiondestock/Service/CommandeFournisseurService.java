package com.example.gestiondestock.Service;

import java.math.BigDecimal;
import java.util.List;

import com.example.gestiondestock.DTO.CommandeFournisseurDto;
import com.example.gestiondestock.DTO.LigneCommandeFournisseurDto;
import com.example.gestiondestock.model.EtatCommande;

public interface CommandeFournisseurService {
	CommandeFournisseurDto save(CommandeFournisseurDto dto);
	CommandeFournisseurDto findById(Integer id);
	CommandeFournisseurDto updateEtatCommande(Integer idCommande ,EtatCommande etatCommande);
	CommandeFournisseurDto updateQuantiteCommande(Integer idCommande ,Integer idLigneCommande, BigDecimal quantite);
	CommandeFournisseurDto updatePrixUnitaire(Integer idCommande ,Integer idLigneCommande, BigDecimal prixUnitaire);
	CommandeFournisseurDto updateFournisseur(Integer idCommande ,Integer idFournisseur);
	CommandeFournisseurDto updateArticle(Integer idCommande ,Integer idLigneCommande ,Integer idArticle);
	CommandeFournisseurDto deleteArticle(Integer idCommande,Integer idLigneCommande);

	List<CommandeFournisseurDto> findAll();
	List<LigneCommandeFournisseurDto>findAllLignesCommandesFournisseurByCommandeFournisseurId(Integer idcommandefournisseur);

	void delete(Integer id);
}
