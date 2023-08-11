package com.example.gestiondestock.Service.Impl;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.gestiondestock.DTO.ArticleDto;
import com.example.gestiondestock.DTO.CommandeFournisseurDto;
import com.example.gestiondestock.DTO.FactureFournisseurDto;
import com.example.gestiondestock.DTO.FactureFournisseurDto;
import com.example.gestiondestock.DTO.LigneCommandeFournisseurDto;
import com.example.gestiondestock.Service.CommandeFournisseurService;
import com.example.gestiondestock.Service.FactureFournisseurService;
import com.example.gestiondestock.exception.EntityNotFoundException;
import com.example.gestiondestock.exception.ErrorCodes;
import com.example.gestiondestock.model.ArticleDetails;
import com.example.gestiondestock.model.FactureFournisseur;
import com.example.gestiondestock.model.FactureFournisseur;
import com.example.gestiondestock.model.LigneCommandeFournisseur;

import com.example.gestiondestock.repository.FactureFournisseurRepository;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class FactureFournisseurServiceImpl implements FactureFournisseurService {

	
	private final FactureFournisseurRepository factureFournisseurRepository;
	private final CommandeFournisseurService commandeFournisseurService;


    @Autowired
    public FactureFournisseurServiceImpl(FactureFournisseurRepository factureFournisseurRepository, CommandeFournisseurService commandeFournisseurService) {
        this.factureFournisseurRepository = factureFournisseurRepository;
		this.commandeFournisseurService = commandeFournisseurService;
    }

	@Override
	public List<FactureFournisseurDto> getAllFactureFournisseurs() {
		// TODO Auto-generated method stub

		return factureFournisseurRepository.findAll().stream().map(FactureFournisseurDto::fromEntity).collect(Collectors.toList());
				}

	@Override
	public FactureFournisseurDto getFactureFournisseurById(Integer id) {
		if(id == null) {
			log.error("facture Fournisseur Id is null");
				return null;
		}		
		return factureFournisseurRepository.findById(id)
                .map(FactureFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune facture Fournisseur n'a été trouvée avec l'ID " + id,
                        ErrorCodes.FACTURE_Fournisseur_NOT_FOUND));
	}

	@Override
	public FactureFournisseurDto createFactureFournisseur(Integer idCommandeFournisseur) {
	    // Retrieve the CommandeFournisseur entity based on the given idCommandeFournisseur
		 CommandeFournisseurDto commandeFournisseurdto = commandeFournisseurService.findById(idCommandeFournisseur);
		            
		 if(commandeFournisseurdto==null) {
			 throw new EntityNotFoundException("CommandeFournisseur with id " + idCommandeFournisseur + " not found");
		 }
	    // Create a new FactureFournisseurDto
	    FactureFournisseur factureFournisseur= new FactureFournisseur();
	    factureFournisseur.setCommandeFournisseur(CommandeFournisseurDto.toEntity(commandeFournisseurdto)); // Assuming you have a converter method to convert CommandeFournisseur to CommandeFournisseurDto
	    factureFournisseur.setDateFacture(Instant.now()); // Set the date to the current timestamp
	    factureFournisseur.setCode("FACT-" + Instant.now().toEpochMilli()); // Generate a unique code for the facture

	    // Save the FactureFournisseurDto entity
	    FactureFournisseur factureFournisseurEntity = factureFournisseurRepository.save(factureFournisseur);

	    // Convert the saved entity back to DTO and return
	    return FactureFournisseurDto.fromEntity(factureFournisseurEntity);
	}
	
	@Override
	public FactureFournisseurDto updateFactureFournisseur(Integer id, FactureFournisseurDto factureFournisseur) {
        FactureFournisseur existingFactureFournisseur = factureFournisseurRepository.findById(id).orElse(null);
        if (existingFactureFournisseur != null) {
        	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String loggedInUser = authentication.getName();
            factureFournisseur.setLastModifiedBy(loggedInUser);
        
            return FactureFournisseurDto.fromEntity( factureFournisseurRepository.save(FactureFournisseurDto.toEntity(factureFournisseur)));

        }
        return null;
	}

	@Override
	public void deleteFactureFournisseur(Integer id) {
		factureFournisseurRepository.deleteById(id);		
	}

	@Override
	public double calculateMontantFactureFournisseur(Integer id) {
		 FactureFournisseur factureFournisseur = factureFournisseurRepository.findById(id).orElse(null);
	        if (factureFournisseur == null) {
	            // Gérer le cas où la facture n'existe pas
	            return 0.0;
	        }
	        
	        double montantFacture = 0.0;
	        List<LigneCommandeFournisseur> ligneCommandeFournisseurs = factureFournisseur.getCommandeFournisseur().getLigneCommandefournisseur();
	        
	        for (LigneCommandeFournisseur ligneCommandeFournisseur : ligneCommandeFournisseurs) {
	            BigDecimal montantLigne = ligneCommandeFournisseur.getQuantite().multiply(ligneCommandeFournisseur.getPrixUnitaire()  );
	            montantFacture += montantLigne.doubleValue(); // Convert BigDecimal to double before adding

	        }
	        
	        return montantFacture;
	}
	public List<ArticleDetails> getArticlesAndPrixQuantite(FactureFournisseur factureFournisseur) {
	    List<ArticleDetails> articleDetails = new ArrayList<>();
	    Integer idCommandeFournisseur = factureFournisseur.getCommandeFournisseur().getId();
	    List<LigneCommandeFournisseurDto> lignesCommandeFournisseur = commandeFournisseurService.findAllLignesCommandesFournisseurByCommandeFournisseurId(idCommandeFournisseur);
	    
	    for (LigneCommandeFournisseurDto ligneCommandeFournisseur : lignesCommandeFournisseur) {
	        ArticleDto article = ligneCommandeFournisseur.getArticle();
	        String articleName = article.getCodeArticle(); // Replace with the actual method to get the article name

	        // Assuming you have methods to retrieve prixUnitaire and quantite from LigneCommandeFournisseur
	        // Replace getPrixUnitaire() and getQuantite() with the actual method names if different.
	        String prixUnitaire = ligneCommandeFournisseur.getPrixUnitaire().toString();
	        String quantite = ligneCommandeFournisseur.getQuantite().toString();

	        ArticleDetails articleDetail = new ArticleDetails();
	        articleDetail.setArticleName(articleName);
	        articleDetail.setPrixUnitaire(prixUnitaire);
	        articleDetail.setQuantite(quantite);
	        articleDetails.add(articleDetail);
	    }

	    return articleDetails;
	}


}
