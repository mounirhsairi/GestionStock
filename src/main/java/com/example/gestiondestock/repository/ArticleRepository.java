package com.example.gestiondestock.repository;

import java.util.List;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestiondestock.model.Article;

public interface ArticleRepository extends JpaRepository< Article ,Integer> {
	List<Article> findAllByCodeArticle(String codeArticle);
	Optional<Article>findArticleByDesignation(String Designation);
    List<Article> findAllByDesignation(String designation);
    List<Article> findAllByCategoryId(Integer idCategory);


}
