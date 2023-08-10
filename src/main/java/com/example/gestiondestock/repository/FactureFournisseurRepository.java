package com.example.gestiondestock.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestiondestock.model.FactureFournisseur;

public interface FactureFournisseurRepository extends JpaRepository<FactureFournisseur,Integer> {

	List<FactureFournisseur> findAll();
	Optional<FactureFournisseur> findById(Integer id);


}
