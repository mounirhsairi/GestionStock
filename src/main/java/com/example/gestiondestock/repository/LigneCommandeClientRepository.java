package com.example.gestiondestock.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestiondestock.model.LigneCommandeClient;

public interface LigneCommandeClientRepository  extends JpaRepository<LigneCommandeClient ,Integer>{
List<LigneCommandeClient> findAllByCommandeClientId(Integer commandeClientId);
List<LigneCommandeClient>findAllByArticleId(Integer Id);
}
