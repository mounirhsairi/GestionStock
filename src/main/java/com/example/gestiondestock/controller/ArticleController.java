package com.example.gestiondestock.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestiondestock.DTO.ArticleDto;
import com.example.gestiondestock.DTO.LigneCommandeClientDto;
import com.example.gestiondestock.DTO.LigneCommandeFournisseurDto;
import com.example.gestiondestock.DTO.LigneVenteDto;
import com.example.gestiondestock.Service.ArticleService;
import com.example.gestiondestock.controller.api.ArticleApi;
@RestController
public class ArticleController implements ArticleApi {
	private ArticleService articleService ;
	@Autowired
	public ArticleController(ArticleService articleService) {
		
		this.articleService = articleService;
	}
	@Override
	public ArticleDto save(ArticleDto dto) {
		return articleService.save(dto) ;
	}

	

	@Override
	public ArticleDto findById(Integer id) {
		// TODO Auto-generated method stub
		return articleService.findById(id);
	}

	@Override
	public List<ArticleDto> findAllByCodeArticle(String code) {
		// TODO Auto-generated method stub
		return articleService.findAllByCodeArticle(code);
	}

	@Override
	public List<ArticleDto> findAll() {
		// TODO Auto-generated method stub
		return articleService.findAll();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		articleService.delete(id);
	}
	
	
	@Override
	public ArticleDto updateArticle(Integer articleId, ArticleDto articleDto) {
		// TODO Auto-generated method stub
		return articleService.updateArticle(articleId, articleDto);
	}
	@Override
	public List<ArticleDto> findAllByDesignationArticle(String designation) {
		return articleService.findAllByDesignation(designation);
	}
	@Override
	public List<LigneVenteDto> findHistoriqueVentes(Integer idArticle) {
		// TODO Auto-generated method stub
		return articleService.findHistoriqueVentes(idArticle);
	}
	@Override
	public List<LigneCommandeClientDto> findHistoriqueCommandesClient(Integer idArticle) {
		// TODO Auto-generated method stub
		return articleService.findHistoriqueCommandesClient(idArticle);	}
	@Override
	public List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle) {
		// TODO Auto-generated method stub
		return articleService.findHistoriqueCommandeFournisseur(idArticle);
	}
	@Override
	public List<ArticleDto> findAllArticleByIdCategory(Integer idCategory) {
		// TODO Auto-generated method stub
		return articleService.findAllArticleByIdCategory(idCategory);
	}
	
	
	
	
	
	
	
}

