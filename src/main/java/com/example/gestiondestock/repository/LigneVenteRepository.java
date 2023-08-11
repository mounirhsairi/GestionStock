package com.example.gestiondestock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestiondestock.model.CommandeClient;
import com.example.gestiondestock.model.LigneVente;

public interface LigneVenteRepository extends JpaRepository < LigneVente,Integer>{

	List<LigneVente> findAllByArticleId(Integer idArticle);

	List<LigneVente> findAllByVenteId(Integer id);

	List<LigneVente> findByCommandeClient(CommandeClient savedCmdCLt);
}
