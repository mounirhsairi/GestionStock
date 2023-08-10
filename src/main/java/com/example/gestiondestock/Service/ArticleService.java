package com.example.gestiondestock.Service;

import java.util.List;


import com.example.gestiondestock.DTO.ArticleDto;
import com.example.gestiondestock.DTO.LigneCommandeClientDto;
import com.example.gestiondestock.DTO.LigneCommandeFournisseurDto;
import com.example.gestiondestock.DTO.LigneVenteDto;

public interface ArticleService {
	ArticleDto save(ArticleDto dto);
	ArticleDto findById(Integer id);
	List<ArticleDto> findAllByCodeArticle(String code);
	ArticleDto updateArticle(Integer idArticle, ArticleDto articleDto);
	List<ArticleDto> findAllByDesignation(String designation);
	List<ArticleDto> findAll();
	List<LigneVenteDto> findHistoriqueVentes(Integer idArticle) ;
	List<LigneCommandeClientDto> findHistoriqueCommandesClient(Integer idArticle) ;
	List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle) ;
	List<ArticleDto> findAllArticleByIdCategory(Integer idCategory) ;

	void delete(Integer id);
}
