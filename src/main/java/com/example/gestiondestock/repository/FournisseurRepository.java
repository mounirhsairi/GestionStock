package com.example.gestiondestock.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestiondestock.model.Fournisseur;

public interface FournisseurRepository extends JpaRepository<Fournisseur ,Integer> {
}
