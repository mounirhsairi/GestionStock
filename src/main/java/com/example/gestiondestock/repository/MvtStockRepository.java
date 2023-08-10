package com.example.gestiondestock.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.gestiondestock.model.MvnStock;

public interface MvtStockRepository extends JpaRepository <MvnStock ,Integer >{

	@Query("select sum(m.quantite) from MvnStock m where m.article.id = :idArticle")
	BigDecimal stockReelArticle(@Param("idArticle")Integer idArticle);
	
	List<MvnStock>findAllByArticleId(Integer idArticle);
}
