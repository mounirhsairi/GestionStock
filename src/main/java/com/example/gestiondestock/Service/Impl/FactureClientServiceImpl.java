package com.example.gestiondestock.Service.Impl;

import java.math.BigDecimal;

import java.util.ArrayList;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.gestiondestock.DTO.ArticleDto;
import com.example.gestiondestock.DTO.CommandeClientDto;
import com.example.gestiondestock.DTO.FactureClientDto;
import com.example.gestiondestock.DTO.LigneCommandeClientDto;
import com.example.gestiondestock.Service.CommandeClientService;
import com.example.gestiondestock.Service.FactureClientService;
import com.example.gestiondestock.exception.EntityNotFoundException;
import com.example.gestiondestock.exception.ErrorCodes;
import com.example.gestiondestock.model.ArticleDetails;
import com.example.gestiondestock.model.FactureClient;
import com.example.gestiondestock.model.LigneCommandeClient;
import com.example.gestiondestock.repository.FactureClientRepository;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class FactureClientServiceImpl implements FactureClientService {

	
	private final FactureClientRepository factureClientRepository;
	private final CommandeClientService commandeClientService;


    @Autowired
    public FactureClientServiceImpl(FactureClientRepository factureClientRepository, CommandeClientService commandeClientService) {
        this.factureClientRepository = factureClientRepository;
		this.commandeClientService = commandeClientService;
    }

	@Override
	public List<FactureClientDto> getAllFactureClients() {
		// TODO Auto-generated method stub
		return factureClientRepository.findAll().stream().map(FactureClientDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public FactureClientDto getFactureClientById(Integer id) {
		if(id == null) {
			log.error("facture client Id is null");
				return null;
		}		
		return factureClientRepository.findById(id)
                .map(FactureClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune facture client n'a été trouvée avec l'ID " + id,
                        ErrorCodes.FACTURE_CLIENT_NOT_FOUND));
	}
	@Override
	public FactureClientDto createFactureClient(FactureClientDto factureClient) {

	   
          return 	FactureClientDto.fromEntity(factureClientRepository.save(FactureClientDto.toEntity(factureClient)))    ;
	}

	

	   

	

	@Override
	public FactureClientDto updateFactureClient(Integer id, FactureClientDto factureClient) {
		 FactureClient existingFactureClient = factureClientRepository.findById(id).orElse(null);
	        if (existingFactureClient != null) {
	            
	        	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	            String loggedInUser = authentication.getName();
	            factureClient.setLastModifiedBy(loggedInUser);
	            return FactureClientDto.fromEntity( factureClientRepository.save(FactureClientDto.toEntity(factureClient) ));
	        }
	        return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		factureClientRepository.deleteById(id);
	}

	@Override
	public double calculateMontantFactureClient(Integer id) {
		 FactureClient factureClient = factureClientRepository.findById(id).orElse(null);
	        if (factureClient == null) {
	            // Gérer le cas où la facture n'existe pas
	            return 0.0;
	        }
	        
	        double montantFacture = 0.0;
	        List<LigneCommandeClient> ligneCommandeClients = factureClient.getCommandeClient().getLignecommandeclient();
	        
	        for (LigneCommandeClient ligneCommandeClient : ligneCommandeClients) {
	            BigDecimal montantLigne = ligneCommandeClient.getQuantite().multiply(ligneCommandeClient.getPrixUnitaire()  );
	            montantFacture += montantLigne.doubleValue(); // Convert BigDecimal to double before adding

	        }
	        
	        return montantFacture;
	    }
	public List<ArticleDetails> getArticlesAndPrixQuantite(FactureClient factureClient) {
        List<ArticleDetails> articleDetails = new ArrayList<>();
        Integer idcommandeclient =  factureClient.getCommandeClient().getId();
        List<LigneCommandeClientDto> lignesCommandeClient = commandeClientService.findAllLignesCommandesClientByCommandeClientId(idcommandeclient);
        for (LigneCommandeClientDto ligneCommandeClient : lignesCommandeClient) {
            ArticleDto article = ligneCommandeClient.getArticle();
            String articleName = article.getCodeArticle(); // Replace with the actual method to get the article name

            // Assuming you have methods to retrieve prixUnitaire and quantite from LigneCommandeClient
            // Replace getPrixUnitaire() and getQuantite() with the actual method names if different.
            String prixUnitaire = ligneCommandeClient.getPrixUnitaire().toString();
            String quantite = ligneCommandeClient.getQuantite().toString();

            ArticleDetails articleDetail = new ArticleDetails();
            articleDetail.setArticleName(articleName);
            articleDetail.setPrixUnitaire(prixUnitaire);
            articleDetail.setQuantite(quantite);
            articleDetails.add(articleDetail);
        }

        return articleDetails;
    }

	
	}
	
	
	
	


