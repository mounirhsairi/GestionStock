package com.example.gestiondestock.DTO;

import java.math.BigDecimal;

import com.example.gestiondestock.model.Article;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ArticleDto {
	private Integer id ;
	 private String codeArticle ;
	 
	private String designation;
	
	private BigDecimal prixUnitaireHt ;
	
	private BigDecimal TauxTva ;
	
	private BigDecimal prixUnitaireTtc ;
	
	private String photo ;
    
	private CategoryDto category ;
	 private String lastModifiedBy ;

	 
	public static ArticleDto fromEntity (Article article)
	{
		if(article == null) {
			return null ;
		}
		return ArticleDto.builder()
				.id(article.getId())
				.codeArticle(article.getCodeArticle())
				.prixUnitaireHt(article.getPrixUnitaireHt())
				.designation(article.getDesignation())
				.TauxTva(article.getTauxTva())
				.prixUnitaireTtc(article.getPrixUnitaireTtc())
				.photo(article.getPhoto())
				.category(CategoryDto.fromEntity(article.getCategory()))
				.lastModifiedBy(article.getLastModifiedBy())
				.build() ;
	}
	 public static Article toEntity(ArticleDto articleDto) {
	        Article article = new Article();
	        article.setId(articleDto.getId());
	        article.setCodeArticle(articleDto.getCodeArticle());
	        article.setDesignation(articleDto.getDesignation());
	        article.setPrixUnitaireTtc(articleDto.getPrixUnitaireTtc());
	        article.setPrixUnitaireHt(articleDto.getPrixUnitaireHt());
	        article.setTauxTva(articleDto.getTauxTva());
	        article.setPhoto(articleDto.getPhoto());
	        article.setCategory(CategoryDto.toEntity(articleDto.getCategory()));
	        article.setLastModifiedBy(articleDto.getLastModifiedBy());
	        return article;
	 }
}
