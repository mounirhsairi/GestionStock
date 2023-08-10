package com.example.gestiondestock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestiondestock.model.LigneCommandeFournisseur;

public interface LigneCommandeFournisseurRepository extends JpaRepository <LigneCommandeFournisseur,Integer > {

	List<LigneCommandeFournisseur> findAllByArticleId(Integer idArticle);

	List<LigneCommandeFournisseur> findAllByCommandeFournisseurId(Integer id);

}
