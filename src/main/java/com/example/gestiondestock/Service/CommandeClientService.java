package com.example.gestiondestock.Service;

import java.math.BigDecimal;
import java.util.List;

import com.example.gestiondestock.DTO.CommandeClientDto;
import com.example.gestiondestock.DTO.LigneCommandeClientDto;
import com.example.gestiondestock.model.EtatCommande;

public interface CommandeClientService {

	CommandeClientDto save(CommandeClientDto dto);
	CommandeClientDto updateEtatCommande(Integer idCommande ,EtatCommande etatCommande);
	CommandeClientDto updateQuantiteCommande(Integer idCommande ,Integer idLigneCommande, BigDecimal quantite);
	CommandeClientDto updatePrixUnitaire(Integer idCommande ,Integer idLigneCommande, BigDecimal prixUnitaire);
	CommandeClientDto updateClient(Integer idCommande ,Integer idClient);
	CommandeClientDto updateArticle(Integer idCommande ,Integer idLigneCommande ,Integer idArticle);
	CommandeClientDto deleteArticle(Integer idCommande,Integer idLigneCommande);
	List<LigneCommandeClientDto>findAllLignesCommandesClientByCommandeClientId(Integer idcommandeclient);
	CommandeClientDto findById(Integer id);
	List<CommandeClientDto> findAll();
	void delete(Integer id);
}
