package com.example.gestiondestock.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestiondestock.model.CommandeFournisseur;

public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Integer> {

	List<CommandeFournisseur> findAllByFournisseurId(Integer id);

}
