package com.example.gestiondestock.Service.Impl;

import java.util.List;


import java.util.ArrayList;



import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.gestiondestock.DTO.ArticleDto;
import com.example.gestiondestock.DTO.CategoryDto;
import com.example.gestiondestock.DTO.LigneCommandeClientDto;
import com.example.gestiondestock.DTO.LigneCommandeFournisseurDto;
import com.example.gestiondestock.DTO.LigneVenteDto;
import com.example.gestiondestock.Service.ArticleService;
import com.example.gestiondestock.exception.EntityNotFoundException;
import com.example.gestiondestock.exception.ErrorCodes;
import com.example.gestiondestock.exception.InvalidEntityException;
import com.example.gestiondestock.exception.InvalidOperationException;
import com.example.gestiondestock.model.Article;
import com.example.gestiondestock.model.Category;
import com.example.gestiondestock.model.LigneCommandeClient;
import com.example.gestiondestock.model.LigneCommandeFournisseur;
import com.example.gestiondestock.model.LigneVente;
import com.example.gestiondestock.repository.ArticleRepository;
import com.example.gestiondestock.repository.LigneCommandeClientRepository;
import com.example.gestiondestock.repository.LigneCommandeFournisseurRepository;
import com.example.gestiondestock.repository.LigneVenteRepository;
import com.example.gestiondestock.validator.ArticleValidator;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {
	

	private ArticleRepository articleRepository ;
	private LigneVenteRepository venteRepository ;
	private LigneCommandeClientRepository commandeClientRepository ;
	private LigneCommandeFournisseurRepository commandeFournisseurRepository ;
	
	@Autowired
	public ArticleServiceImpl(ArticleRepository articleRepository, LigneVenteRepository venteRepository,
			LigneCommandeClientRepository commandeClientRepository,
			LigneCommandeFournisseurRepository commandeFournisseurRepository) {
		this.articleRepository = articleRepository;
		this.venteRepository = venteRepository;
		this.commandeClientRepository = commandeClientRepository;
		this.commandeFournisseurRepository = commandeFournisseurRepository;
	}
	
	

	@Override
	public ArticleDto save(ArticleDto dto) {
		List<String> errors = ArticleValidator.validate(dto);
			if(!errors.isEmpty()) {
				log.error("Article is not valid");
				throw new InvalidEntityException("l'article n'est pas valide ",ErrorCodes.ARTICLE_NOT_VALID,errors);
			}
		return ArticleDto.fromEntity(articleRepository.save(ArticleDto.toEntity(dto)));
	}

	/*@Override
	public List<ArticleDto> findByDesignationContainingIgnoreCase(String designation) {
	    Optional<Article> articles = articleRepository.findByDesignationContainingIgnoreCase(designation);
	    if (articles.isEmpty()) {
	        throw new EntityNotFoundException("Aucun article avec la désignation " + designation, ErrorCodes.ARTICLE_NOT_FOUND);
	    }
	    return articles.stream()
	            .map(ArticleDto::fromEntity)
	            .collect(Collectors.toList());
	}*/


	@Override
	public ArticleDto findById(Integer id) {
		if(id == null) {
		log.error("Article Id is null");
			return null;
	}
		Optional<Article> article =articleRepository.findById(id) ;
		return Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(()-> new EntityNotFoundException("aucun article avec le code "+ id,ErrorCodes.ARTICLE_NOT_FOUND));
	}
	@Override
	public List<ArticleDto> findAllByCodeArticle(String codeArticle) {
		if(!StringUtils.hasLength(codeArticle))
		{
			log.error("Article Code is null");
		return null;
		}
		List<Article> article =articleRepository.findAllByCodeArticle(codeArticle) ;
		if (article.isEmpty()) {
	        throw new EntityNotFoundException("Aucun article avec l'ID " + codeArticle, ErrorCodes.ARTICLE_NOT_FOUND);
	    }

	    return article.stream()
	            .map(ArticleDto::fromEntity)
	            .collect(Collectors.toList());
	}

		

	@Override
	public List<ArticleDto> findAllByDesignation(String designation) {
		// TODO Auto-generated method stub
		 return articleRepository.findAllByDesignation(designation).stream().map(ArticleDto::fromEntity).collect(Collectors.toList());
	}
	
	


	

	
	@Override
	public List<ArticleDto> findAll() {

		return articleRepository.findAll().stream().map(ArticleDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		if(id == null) {
			log.error("Article Id is null");
				return ;
		}
		List<LigneCommandeClient>ligneCommandeClients =commandeClientRepository.findAllByArticleId(id);
		if(!ligneCommandeClients.isEmpty())
		{
			throw new InvalidOperationException("impossible de supprimer un article deja utiliser dans des commandes clients",ErrorCodes.ARTICLE_ALLREADY_IN_USE);
		}
		List<LigneCommandeFournisseur>ligneCommandeFournisseurs =commandeFournisseurRepository.findAllByArticleId(id);
		if(!ligneCommandeFournisseurs.isEmpty())
		{
			throw new InvalidOperationException("impossible de supprimer un article deja utiliser dans des commandes fournisseurs",ErrorCodes.ARTICLE_ALLREADY_IN_USE);
		}
		List<LigneVente>ligneVente =venteRepository.findAllByArticleId(id);
		if(!ligneVente.isEmpty())
		{
			throw new InvalidOperationException("impossible de supprimer un article deja utiliser dans des ventes",ErrorCodes.ARTICLE_ALLREADY_IN_USE);
		}
			articleRepository.deleteById(id);
		
	}

	@Override
	public List<LigneVenteDto> findHistoriqueVentes(Integer idArticle) {
		// TODO Auto-generated method stub
		return venteRepository.findAllByArticleId(idArticle).stream().map(LigneVenteDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public List<LigneCommandeClientDto> findHistoriqueCommandesClient(Integer idArticle) {
		// TODO Auto-generated method stub
		return commandeClientRepository.findAllByArticleId(idArticle).stream().map(LigneCommandeClientDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle) {
		// TODO Auto-generated method stub
		return commandeFournisseurRepository.findAllByArticleId(idArticle).stream().map(LigneCommandeFournisseurDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public List<ArticleDto> findAllArticleByIdCategory(Integer idCategory) {
		// TODO Auto-generated method stub
		return articleRepository.findAllByCategoryId(idCategory).stream().map(ArticleDto::fromEntity).collect(Collectors.toList());
	}



	@Override
	public ArticleDto updateArticle(Integer idArticle, ArticleDto articleDto) {

		if(idArticle == null) {
			log.error("Article ID is Null");

            throw new InvalidOperationException("impossible de modifier un article avec un id null", ErrorCodes.ARTICLE_NOT_VALID);
		}
		Optional<Article> articleOptional =articleRepository.findById(idArticle);
    	if(articleOptional.isEmpty())
    	{
    		throw new InvalidEntityException("Aucune category avec l'ID " +idArticle
                    + " n'a été trouvé dans la base de données", ErrorCodes.ARTICLE_NOT_FOUND);
        
    	}
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUser = authentication.getName();
        articleDto.setLastModifiedBy(loggedInUser);
    	
    	
    	return ArticleDto.fromEntity(articleRepository.save(ArticleDto.toEntity(articleDto)));
	}

	
	

	

	

}

	


