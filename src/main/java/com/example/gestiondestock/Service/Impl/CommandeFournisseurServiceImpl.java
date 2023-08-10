package com.example.gestiondestock.Service.Impl;

import java.math.BigDecimal;

import java.time.Instant;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.gestiondestock.DTO.ArticleDto;

import com.example.gestiondestock.DTO.CommandeFournisseurDto;
import com.example.gestiondestock.DTO.FournisseurDto;
import com.example.gestiondestock.DTO.LigneCommandeFournisseurDto;
import com.example.gestiondestock.DTO.MvnStockDto;
import com.example.gestiondestock.Service.CommandeFournisseurService;
import com.example.gestiondestock.Service.MvStkService;
import com.example.gestiondestock.exception.EntityNotFoundException;
import com.example.gestiondestock.exception.ErrorCodes;
import com.example.gestiondestock.exception.InvalidEntityException;
import com.example.gestiondestock.exception.InvalidOperationException;
import com.example.gestiondestock.model.Article;
import com.example.gestiondestock.model.CommandeFournisseur;
import com.example.gestiondestock.model.EtatCommande;
import com.example.gestiondestock.model.Fournisseur;
import com.example.gestiondestock.model.LigneCommandeFournisseur;
import com.example.gestiondestock.model.SourceMvtStock;
import com.example.gestiondestock.model.TypeMvtStock;
import com.example.gestiondestock.repository.ArticleRepository;
import com.example.gestiondestock.repository.CommandeFournisseurRepository;
import com.example.gestiondestock.repository.FournisseurRepository;
import com.example.gestiondestock.repository.LigneCommandeFournisseurRepository;
import com.example.gestiondestock.validator.ArticleValidator;
import com.example.gestiondestock.validator.CommandeFournisseurValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private CommandeFournisseurRepository commandeFournisseurRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    private FournisseurRepository fournisseurRepository;
    private ArticleRepository articleRepository;
    private MvStkService mvtStkService;

    @Autowired
    public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository,
                                          LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository,
                                          FournisseurRepository fournisseurRepository, ArticleRepository articleRepository, MvStkService mvtStkService) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.articleRepository = articleRepository;
        this.mvtStkService= mvtStkService;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
        List<String> errors = CommandeFournisseurValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("La commande n'est pas valide");
            throw new InvalidEntityException("La commande n'est pas valide", ErrorCodes.COMMANDE_FOURNISSEUR_INVALID);
        }

        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(dto.getFournisseur().getId());
        if (fournisseur.isEmpty()) {
            log.warn("Fournisseur with ID {} not found in Db", dto.getFournisseur().getId());
            throw new EntityNotFoundException("Aucun fournisseur avec l'ID " + dto.getFournisseur().getId() + " n'a été trouvé dans la BDD",
                    ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();
        if (dto.getLigneCommandefournisseur() != null) {
            dto.getLigneCommandefournisseur().forEach(ligCmdFr -> {
                if (ligCmdFr.getArticle() != null) {
                    Optional<Article> article = articleRepository.findById(ligCmdFr.getArticle().getId());
                    if (article.isEmpty()) {
                        articleErrors.add("L'article avec l'ID " + ligCmdFr.getArticle().getId() + " n'existe pas");
                    }
                } else {
                    articleErrors.add("Impossible d'enregistrer les commandes avec un article null");
                }
            });
        }

        if (!articleErrors.isEmpty()) {
            log.warn("Articles not found in the database");
            throw new InvalidEntityException("Les articles n'existent pas dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND);
        }
        Fournisseur fournisseurr =fournisseur.get();
        dto.setFournisseur(FournisseurDto.fromEntity(fournisseurr));

        CommandeFournisseur savedCmdFr = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(dto));

        if (dto.getLigneCommandefournisseur() != null) {
            dto.getLigneCommandefournisseur().forEach(ligCmdFr -> {
                LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(ligCmdFr);
                ligneCommandeFournisseur.setCommandeFournisseur(savedCmdFr);
                ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
            });
        }

        return CommandeFournisseurDto.fromEntity(savedCmdFr);
    }

    // Rest of the code...


	@Override
	public CommandeFournisseurDto findById(Integer id) {if(id == null) {
		log.error("Commande Fournisseur ID is null");
		return  null ;
	}
	
	return commandeFournisseurRepository.findById(id).map(CommandeFournisseurDto::fromEntity).orElseThrow(()->new EntityNotFoundException("Aucune commande fournisseur n'a ete trouve avec l'ID"+ id,ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND));
}


	
	

	@Override
	public List<CommandeFournisseurDto> findAll() {
		return commandeFournisseurRepository.findAll().stream().map(CommandeFournisseurDto::fromEntity).collect(Collectors.toList());

	}

	@Override
	public void delete(Integer id) {

		if(id == null) {
			log.error("Commande fournisseur ID is null");
			return   ;
		}
		List<LigneCommandeFournisseur>ligneCommandeFournisseur =ligneCommandeFournisseurRepository.findAllByCommandeFournisseurId(id);
        if(!ligneCommandeFournisseur.isEmpty())
        {
			throw new InvalidOperationException("impossible de supprimer une commande fournisseur qui est deja des lignes commandes fournisseur ",ErrorCodes.COMMANDE_FOURNISSEUR_ALLREADY_IN_USE);

        }
		commandeFournisseurRepository.deleteById(id);
	}

	@Override
	public CommandeFournisseurDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande,
			BigDecimal quantite) {
		if(idCommande == null)
    	{
    		log.error("commande Fournisseur ID is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un id null", ErrorCodes.COMMANDE_FOURNISSEUR_INVALID);
    	}
    	if(idLigneCommande == null)
    	{
    		log.error("Ligne commande Fournisseur ID is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un id null", ErrorCodes.COMMANDE_FOURNISSEUR_INVALID);
    	}
    	if(quantite == null && quantite.compareTo(BigDecimal.ZERO)==0)
    	{
    		log.error("Ligne commande Fournisseur ID is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec une quantite null ou egale a zero", ErrorCodes.COMMANDE_FOURNISSEUR_INVALID);
    	}
   
    	CommandeFournisseurDto commandeFournisseur = findById(idCommande);
    	if( commandeFournisseur.isCommandeLivree())
        {
        	throw new InvalidOperationException("impossible de modifier la commande losque elle est livree" , ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND);
        
        }
    	Optional<LigneCommandeFournisseur> ligneCommandeFournisseurOptional =ligneCommandeFournisseurRepository.findById(idLigneCommande);
    	if(ligneCommandeFournisseurOptional.isEmpty()) {
    		throw new EntityNotFoundException("Aucune ligne de  commande avec l'ID " + idLigneCommande
                    + " n'a été trouvé dans la base de données", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND);
    	}
    	LigneCommandeFournisseur ligneCommandeFournisseur =ligneCommandeFournisseurOptional.get();
    	ligneCommandeFournisseur.setQuantite(quantite);
    	ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
    	return commandeFournisseur ;
    }

	

	@Override
	public CommandeFournisseurDto updatePrixUnitaire(Integer idCommande, Integer idLigneCommande,
			BigDecimal prixUnitaire) {
		if(idCommande == null)
    	{
    		log.error("commande Fournisseur ID is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un id null", ErrorCodes.COMMANDE_FOURNISSEUR_INVALID);
    	}
    	if(idLigneCommande == null)
    	{
    		log.error("Ligne commande Fournisseur ID is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un id null", ErrorCodes.COMMANDE_FOURNISSEUR_INVALID);
    	}
    	if(prixUnitaire == null && prixUnitaire.compareTo(BigDecimal.ZERO)==0)
    	{
    		log.error("prix is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un prix null ou egale a zero", ErrorCodes.COMMANDE_FOURNISSEUR_INVALID);
    	}
   
    	CommandeFournisseurDto commandeFournisseur = findById(idCommande);
    	if( commandeFournisseur.isCommandeLivree())
        {
        	throw new InvalidEntityException("Aucun Fournisseur avec l'ID " + commandeFournisseur.getFournisseur().getId()
                    + " n'a été trouvé dans la base de données", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND);
        
        }
    	Optional<LigneCommandeFournisseur> ligneCommandeFournisseurOptional =ligneCommandeFournisseurRepository.findById(idLigneCommande);
    	if(ligneCommandeFournisseurOptional.isEmpty()) {
    		throw new EntityNotFoundException("Aucune ligne de  commande avec l'ID " + idLigneCommande
                    + " n'a été trouvé dans la base de données", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND);
    	}
    	LigneCommandeFournisseur ligneCommandeFournisseur =ligneCommandeFournisseurOptional.get();
    	ligneCommandeFournisseur.setPrixUnitaire(prixUnitaire);
    	ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
    	return commandeFournisseur ;
	}

	@Override
	public CommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur) {
		if(idCommande == null)
    	{
    		log.error("commande Fournisseur ID is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un id null", ErrorCodes.COMMANDE_FOURNISSEUR_INVALID);
    	}
    	if(idFournisseur == null)
    	{
    		log.error(" fournisseur ID is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un id fournisseur null", ErrorCodes.COMMANDE_FOURNISSEUR_INVALID);
    	}
    	CommandeFournisseurDto commandeFournisseur = findById(idCommande);
    	if( commandeFournisseur.isCommandeLivree())
        {
        	throw new InvalidOperationException("impossible de modifier  la commande lorqu'elle est livree", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND);
        
        }
    	Optional<Fournisseur> FournisseurOptional =fournisseurRepository.findById(idFournisseur);
    	if(FournisseurOptional.isEmpty())
    	{
    		throw new InvalidEntityException("Aucun Fournisseur avec l'ID " + commandeFournisseur.getFournisseur().getId()
                    + " n'a été trouvé dans la base de données", ErrorCodes.FOURNISSEUR_NOT_FOUND);
        
    	}
    	commandeFournisseur.setFournisseur(FournisseurDto.fromEntity(FournisseurOptional.get()));
    	return CommandeFournisseurDto.fromEntity(commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseur)));
	}
	

	@Override
	public CommandeFournisseurDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
		if(idCommande == null)
    	{
    		log.error("commande Fournisseur ID is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un id null", ErrorCodes.COMMANDE_FOURNISSEUR_INVALID);
    	}
		if(idLigneCommande == null)
    	{
    		log.error("Ligne commande Fournisseur ID is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un id null", ErrorCodes.COMMANDE_FOURNISSEUR_INVALID);
    	}
		
		checkIdArtcile(idArticle,"nouveau");

		CommandeFournisseurDto commandeFournisseur = findById(idCommande);
    	if( commandeFournisseur.isCommandeLivree())
        {
        	throw new InvalidOperationException("impossible de modifier  la commande lorqu'elle est livree", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND);
        
        }
    	Optional<LigneCommandeFournisseur> ligneCommandeFournisseurOptional =ligneCommandeFournisseurRepository.findById(idLigneCommande);
    	if(ligneCommandeFournisseurOptional.isEmpty()) {
    		throw new EntityNotFoundException("Aucune ligne de  commande avec l'ID " + idLigneCommande
                    + " n'a été trouvé dans la base de données", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND);
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
    	LigneCommandeFournisseur ligneCommandeFournisseurToSave= ligneCommandeFournisseurOptional.get();
    	ligneCommandeFournisseurToSave.setArticle(articleOptional.get());
    	ligneCommandeFournisseurRepository.save(ligneCommandeFournisseurToSave);
		return commandeFournisseur;
	}
	private void checkIdArtcile(Integer idArticle ,String msg)
	{
		if(idArticle == null)
    	{
    		log.error("l'ID"+msg+" is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un"+msg+" id null", ErrorCodes.COMMANDE_FOURNISSEUR_INVALID);
    	}
	}
	private void updateMvtStk(Integer idCommande) {
		List<LigneCommandeFournisseur>ligneCommandeFournisseurs =ligneCommandeFournisseurRepository.findAllByCommandeFournisseurId(idCommande);
		ligneCommandeFournisseurs.forEach(lig ->{
			MvnStockDto mvtStkDto = MvnStockDto.builder()
					.article(ArticleDto.fromEntity(lig.getArticle()))
					.dateMvt(Instant.now())
					.typeMvt(TypeMvtStock.ENTREE)
					.sourceMvt(SourceMvtStock.COMMANDE_FOURNISSEUR)
					.quantite(lig.getQuantite())
					.idEntreprise(lig.getIdEntreprise())
					.build();
			
			mvtStkService.sortieStock(mvtStkDto);
		});
		
	}

	@Override
	public List<LigneCommandeFournisseurDto> findAllLignesCommandesFournisseurByCommandeFournisseurId(
			Integer idcommandefournisseur) {
		// TODO Auto-generated method stub
		return ligneCommandeFournisseurRepository.findAllByCommandeFournisseurId(idcommandefournisseur).stream().map(LigneCommandeFournisseurDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public CommandeFournisseurDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
		if(idCommande == null)
    	{
    		log.error("commande Fournisseur ID is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un id null", ErrorCodes.COMMANDE_FOURNISSEUR_INVALID);
    	}
    	if(!StringUtils.hasLength(String.valueOf(etatCommande)))
    	{
    		log.error("l'etat de la commande Fournisseur  is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un id null", ErrorCodes.COMMANDE_FOURNISSEUR_INVALID);
    	}
    	CommandeFournisseurDto commandeFournisseur = findById(idCommande);
    	if(commandeFournisseur.getId()!=null && commandeFournisseur.isCommandeLivree())
        {
        	throw new InvalidEntityException("Aucun Fournisseur avec l'ID " + commandeFournisseur.getFournisseur().getId()
                    + " n'a été trouvé dans la base de données", ErrorCodes.FOURNISSEUR_NOT_FOUND);
        
        }
    	commandeFournisseur.setEtatCommande(etatCommande);
    	CommandeFournisseur savedCmdClt =commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseur));
    	if(commandeFournisseur.isCommandeLivree())
    	{
        	updateMvtStk(idCommande);
    	}
    	return CommandeFournisseurDto.fromEntity(savedCmdClt);
	}

	@Override
	public CommandeFournisseurDto deleteArticle(Integer idCommande, Integer idLigneCommande) {
		if(idCommande == null)
    	{
    		log.error("commande fournisseur ID is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un id null", ErrorCodes.COMMANDE_FOURNISSEUR_INVALID);
    	}
		if(idLigneCommande == null)
    	{
    		log.error("Ligne commande fournisseur ID is Null");
            throw new InvalidOperationException("impossible de modifier l'etat de la commande avec un id null", ErrorCodes.COMMANDE_FOURNISSEUR_INVALID);
    	}
		CommandeFournisseurDto commandeFournisseur = findById(idCommande);
    	if( commandeFournisseur.isCommandeLivree())
        {
        	throw new InvalidOperationException("impossible de modifier  la commande lorqu'elle est livree", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND);
        
        }
    	Optional<LigneCommandeFournisseur> ligneCommandeFournisseurOptional =ligneCommandeFournisseurRepository.findById(idLigneCommande);
    	if(ligneCommandeFournisseurOptional.isEmpty()) {
    		throw new EntityNotFoundException("Aucune ligne de  commande avec l'ID " + idLigneCommande
                    + " n'a été trouvé dans la base de données", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND);
    	}
    	ligneCommandeFournisseurRepository.deleteById(idLigneCommande);
				return commandeFournisseur;
	}
	}
	
	

