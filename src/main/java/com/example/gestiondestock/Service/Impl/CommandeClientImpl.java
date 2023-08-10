package com.example.gestiondestock.Service.Impl;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.gestiondestock.DTO.ArticleDto;
import com.example.gestiondestock.DTO.ClientDto;
import com.example.gestiondestock.DTO.CommandeClientDto;
import com.example.gestiondestock.DTO.LigneCommandeClientDto;
import com.example.gestiondestock.DTO.MvnStockDto;
import com.example.gestiondestock.Service.CommandeClientService;
import com.example.gestiondestock.Service.MvStkService;
import com.example.gestiondestock.exception.EntityNotFoundException;
import com.example.gestiondestock.exception.ErrorCodes;
import com.example.gestiondestock.exception.InvalidEntityException;
import com.example.gestiondestock.exception.InvalidOperationException;
import com.example.gestiondestock.model.Article;
import com.example.gestiondestock.model.Client;
import com.example.gestiondestock.model.CommandeClient;
import com.example.gestiondestock.model.EtatCommande;
import com.example.gestiondestock.model.LigneCommandeClient;
import com.example.gestiondestock.model.SourceMvtStock;
import com.example.gestiondestock.model.TypeMvtStock;
import com.example.gestiondestock.repository.ArticleRepository;
import com.example.gestiondestock.repository.ClientRepository;
import com.example.gestiondestock.repository.CommandeClientRepository;
import com.example.gestiondestock.repository.LigneCommandeClientRepository;
import com.example.gestiondestock.validator.ArticleValidator;
import com.example.gestiondestock.validator.CommandeClientValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommandeClientImpl implements CommandeClientService {

	

	private CommandeClientRepository commandeClientRepository;
    private LigneCommandeClientRepository ligneCommandeClientRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;
    private MvStkService mvtStkService;
    @Autowired
    public CommandeClientImpl(CommandeClientRepository commandeClientRepository, ClientRepository clientRepository,
            LigneCommandeClientRepository ligneCommandeClientRepository, ArticleRepository articleRepository,MvStkService mvtStkService) {
        this.commandeClientRepository = commandeClientRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
        this.mvtStkService=mvtStkService ;
    }
    
	

    @Override
    public CommandeClientDto save(CommandeClientDto dto) {
        List<String> errors = CommandeClientValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("La commande n'est pas valide");
            throw new InvalidOperationException("impossible de modifier la commande lorsqu'elle est livree", ErrorCodes.COMMANDE_CLIENT_INVALID);
        }
        if(dto.getId()!=null && dto.isCommandeLivree())
        {
        	throw new InvalidEntityException("Aucun client avec l'ID " + dto.getClient().getId()
                    + " n'a été trouvé dans la base de données", ErrorCodes.CLIENT_NOT_FOUND);
        
        }

        Optional<Client> client = clientRepository.findById(dto.getClient().getId());
        if (!client.isPresent()) {
            log.warn("Le client avec l'ID {} n'a pas été trouvé dans la base de données", dto.getClient().getId());
            throw new EntityNotFoundException("Aucun client avec l'ID " + dto.getClient().getId()
                    + " n'a été trouvé dans la base de données", ErrorCodes.CLIENT_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();
        if (dto.getLignecommandeclient() != null) {
            for (LigneCommandeClientDto ligCmdClt : dto.getLignecommandeclient()) {
                if (ligCmdClt.getArticle() != null) {
                    Optional<Article> article = articleRepository.findById(ligCmdClt.getArticle().getId());
                    if (article.isEmpty()) {
                        articleErrors.add("L'article avec l'ID " + ligCmdClt.getArticle().getId() + " n'existe pas");
                    }
                } else {
                    articleErrors.add("Impossible d'enregistrer les commandes avec un article null");
                }
            }
        }

        if (!articleErrors.isEmpty()) {
            log.warn("Les articles n'existent pas dans la base de données");
            throw new InvalidEntityException("Certains articles n'existent pas dans la base de données",ErrorCodes.ARTICLE_NOT_FOUND);
        }
        Client clientt =client.get();
        dto.setClient(ClientDto.fromEntity(clientt));
        CommandeClient savedCmdCLt = commandeClientRepository.save(CommandeClientDto.toEntity(dto));

        if (dto.getLignecommandeclient() != null) {
            for (LigneCommandeClientDto ligCmdClt : dto.getLignecommandeclient()) {
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligCmdClt);
                ligneCommandeClient.setCommandeClient(savedCmdCLt);
                ligneCommandeClientRepository.save(ligneCommandeClient);
            }
        }

        return CommandeClientDto.fromEntity(savedCmdCLt);
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        if (id == null) {
            log.error("L'ID de la commande client est null");
            return null;
        }

        return commandeClientRepository.findById(id)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande client n'a été trouvée avec l'ID " + id,
                        ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll()
                .stream()
                .map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("L'ID de la commande client est null");
            return;
        }
        List<LigneCommandeClient>ligneCommandeClient =ligneCommandeClientRepository.findAllByCommandeClientId(id);
        if(!ligneCommandeClient.isEmpty())
        {
			throw new InvalidOperationException("impossible de supprimer une commande client qui est deja des lignes commandes client ",ErrorCodes.COMMANDE_CLIENT_ALLREADY_IN_USE);

        }
        commandeClientRepository.deleteById(id);
    }
    @Override
	public CommandeClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
    	if(idCommande == null)
    	{
    		log.error("commande client ID is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un id null", ErrorCodes.COMMANDE_CLIENT_INVALID);
    	}
    	if(!StringUtils.hasLength(String.valueOf(etatCommande)))
    	{
    		log.error("l'etat de la commande client  is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un id null", ErrorCodes.COMMANDE_CLIENT_INVALID);
    	}
    	CommandeClientDto commandeClient = findById(idCommande);
    	if(commandeClient.getId()!=null && commandeClient.isCommandeLivree())
        {
        	throw new InvalidEntityException("Aucun client avec l'ID " + commandeClient.getClient().getId()
                    + " n'a été trouvé dans la base de données", ErrorCodes.CLIENT_NOT_FOUND);
        
        }
    	commandeClient.setEtatCommande(etatCommande);
    	CommandeClient savedCmdClt =commandeClientRepository.save(CommandeClientDto.toEntity(commandeClient));
    	if(commandeClient.isCommandeLivree())
    	{
        	updateMvtStk(idCommande);
    	}
    	return CommandeClientDto.fromEntity(savedCmdClt);
    }

    @Override
	public CommandeClientDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
    	if(idCommande == null)
    	{
    		log.error("commande client ID is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un id null", ErrorCodes.COMMANDE_CLIENT_INVALID);
    	}
    	if(idLigneCommande == null)
    	{
    		log.error("Ligne commande client ID is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un id null", ErrorCodes.COMMANDE_CLIENT_INVALID);
    	}
    	if(quantite == null && quantite.compareTo(BigDecimal.ZERO)==0)
    	{
    		log.error("Ligne commande client ID is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec une quantite null ou egale a zero", ErrorCodes.COMMANDE_CLIENT_INVALID);
    	}
   
    	CommandeClientDto commandeClient = findById(idCommande);
    	if( commandeClient.isCommandeLivree())
        {
        	throw new InvalidEntityException("Aucun client avec l'ID " + commandeClient.getClient().getId()
                    + " n'a été trouvé dans la base de données", ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);
        
        }
    	Optional<LigneCommandeClient> ligneCommandeClientOptional =ligneCommandeClientRepository.findById(idLigneCommande);
    	if(ligneCommandeClientOptional.isEmpty()) {
    		throw new EntityNotFoundException("Aucune ligne de  commande avec l'ID " + idLigneCommande
                    + " n'a été trouvé dans la base de données", ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);
    	}
    	LigneCommandeClient ligneCommandeClient =ligneCommandeClientOptional.get();
    	ligneCommandeClient.setQuantite(quantite);
    	ligneCommandeClientRepository.save(ligneCommandeClient);
    	return commandeClient ;
    }



	@Override
	public CommandeClientDto updateClient(Integer idCommande, Integer idClient) {
		if(idCommande == null)
    	{
    		log.error("commande client ID is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un id null", ErrorCodes.COMMANDE_CLIENT_INVALID);
    	}
    	if(idClient == null)
    	{
    		log.error(" client ID is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un id client null", ErrorCodes.COMMANDE_CLIENT_INVALID);
    	}
    	CommandeClientDto commandeClient = findById(idCommande);
    	if( commandeClient.isCommandeLivree())
        {
        	throw new InvalidOperationException("impossible de modifier  la commande lorqu'elle est livree", ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);
        
        }
    	Optional<Client> clientOptional =clientRepository.findById(idClient);
    	if(clientOptional.isEmpty())
    	{
    		throw new InvalidEntityException("Aucun client avec l'ID " + commandeClient.getClient().getId()
                    + " n'a été trouvé dans la base de données", ErrorCodes.CLIENT_NOT_FOUND);
        
    	}
    	commandeClient.setClient(ClientDto.fromEntity(clientOptional.get()));
    	return CommandeClientDto.fromEntity(commandeClientRepository.save(CommandeClientDto.toEntity(commandeClient)));
	}



	@Override
	public CommandeClientDto updateArticle(Integer idCommande, Integer idLigneCommande ,Integer idArticle) {
		if(idCommande == null)
    	{
    		log.error("commande client ID is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un id null", ErrorCodes.COMMANDE_CLIENT_INVALID);
    	}
		if(idLigneCommande == null)
    	{
    		log.error("Ligne commande client ID is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un id null", ErrorCodes.COMMANDE_CLIENT_INVALID);
    	}
		
		checkIdArtcile(idArticle,"nouveau");

		CommandeClientDto commandeClient = findById(idCommande);
    	if( commandeClient.isCommandeLivree())
        {
        	throw new InvalidOperationException("impossible de modifier  la commande lorqu'elle est livree", ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);
        
        }
    	Optional<LigneCommandeClient> ligneCommandeClientOptional =ligneCommandeClientRepository.findById(idLigneCommande);
    	if(ligneCommandeClientOptional.isEmpty()) {
    		throw new EntityNotFoundException("Aucune ligne de  commande avec l'ID " + idLigneCommande
                    + " n'a été trouvé dans la base de données", ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);
    	}
    	Optional<Article>articleOptional =articleRepository.findById(idArticle);
    	if(articleOptional.isEmpty())
    	{
    		throw new EntityNotFoundException("Aucun Article  avec l'ID " + idArticle
                    + " n'a été trouvé dans la base de données", ErrorCodes.ARTICLE_NOT_FOUND);
    	}
    	List<String> errors =ArticleValidator.validate(ArticleDto.fromEntity(articleOptional.get()));
    	if(!errors.isEmpty())
    	{
    		throw new InvalidEntityException("",ErrorCodes.ARTICLE_NOT_VALID,errors);
    	}
    	LigneCommandeClient ligneCommandeClientToSave= ligneCommandeClientOptional.get();
    	ligneCommandeClientToSave.setArticle(articleOptional.get());
    	ligneCommandeClientRepository.save(ligneCommandeClientToSave);
		return commandeClient;
	}
	private void checkIdArtcile(Integer idArticle ,String msg)
	{
		if(idArticle == null)
    	{
    		log.error("l'ID"+msg+" is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un"+msg+" id null", ErrorCodes.COMMANDE_CLIENT_INVALID);
    	}
	}



	@Override
	public CommandeClientDto deleteArticle(Integer idCommande, Integer idLigneCommande) {
		if(idCommande == null)
    	{
    		log.error("commande client ID is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un id null", ErrorCodes.COMMANDE_CLIENT_INVALID);
    	}
		if(idLigneCommande == null)
    	{
    		log.error("Ligne commande client ID is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un id null", ErrorCodes.COMMANDE_CLIENT_INVALID);
    	}
		CommandeClientDto commandeClient = findById(idCommande);
    	if( commandeClient.isCommandeLivree())
        {
        	throw new InvalidOperationException("impossible de modifier  la commande lorqu'elle est livree", ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);
        
        }
    	Optional<LigneCommandeClient> ligneCommandeClientOptional =ligneCommandeClientRepository.findById(idLigneCommande);
    	if(ligneCommandeClientOptional.isEmpty()) {
    		throw new EntityNotFoundException("Aucune ligne de  commande avec l'ID " + idLigneCommande
                    + " n'a été trouvé dans la base de données", ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);
    	}
    	ligneCommandeClientRepository.deleteById(idLigneCommande);
				return commandeClient;
	}

	private void updateMvtStk(Integer idCommande) {
		List<LigneCommandeClient>ligneCommandeClients =ligneCommandeClientRepository.findAllByCommandeClientId(idCommande);
		ligneCommandeClients.forEach(lig ->{
			MvnStockDto mvtStkDto = MvnStockDto.builder()
					.article(ArticleDto.fromEntity(lig.getArticle()))
					.dateMvt(Instant.now())
					.typeMvt(TypeMvtStock.SORTIE)
					.sourceMvt(SourceMvtStock.COMMANDE_CLIENT)
					.quantite(lig.getQuantite())
					.idEntreprise(lig.getIdEntreprise())
					.build();
			mvtStkService.sortieStock(mvtStkDto);
		});
		
	}


	@Override
	public List<LigneCommandeClientDto> findAllLignesCommandesClientByCommandeClientId(Integer idcommandeclient) {
		
		return ligneCommandeClientRepository.findAllByCommandeClientId(idcommandeclient)
	            .stream()
	            .map(LigneCommandeClientDto::fromEntity)
	            .collect(Collectors.toList());	}



	@Override
	public CommandeClientDto updatePrixUnitaire(Integer idCommande, Integer idLigneCommande, BigDecimal prixUnitaire) {
		if(idCommande == null)
    	{
    		log.error("commande client ID is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un id null", ErrorCodes.COMMANDE_CLIENT_INVALID);
    	}
    	if(idLigneCommande == null)
    	{
    		log.error("Ligne commande client ID is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un id null", ErrorCodes.COMMANDE_CLIENT_INVALID);
    	}
    	if(prixUnitaire == null && prixUnitaire.compareTo(BigDecimal.ZERO)==0)
    	{
    		log.error("prix is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un prix null ou egale a zero", ErrorCodes.COMMANDE_CLIENT_INVALID);
    	}
   
    	CommandeClientDto commandeClient = findById(idCommande);
    	if( commandeClient.isCommandeLivree())
        {
        	throw new InvalidEntityException("Aucun client avec l'ID " + commandeClient.getClient().getId()
                    + " n'a été trouvé dans la base de données", ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);
        
        }
    	Optional<LigneCommandeClient> ligneCommandeClientOptional =ligneCommandeClientRepository.findById(idLigneCommande);
    	if(ligneCommandeClientOptional.isEmpty()) {
    		throw new EntityNotFoundException("Aucune ligne de  commande avec l'ID " + idLigneCommande
                    + " n'a été trouvé dans la base de données", ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);
    	}
    	LigneCommandeClient ligneCommandeClient =ligneCommandeClientOptional.get();
    	ligneCommandeClient.setPrixUnitaire(prixUnitaire);
    	ligneCommandeClientRepository.save(ligneCommandeClient);
    	return commandeClient ;
	}
	}
    
