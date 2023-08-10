package com.example.gestiondestock.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestiondestock.model.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise,Integer > {
	Entreprise findByNom(String nom);


}
