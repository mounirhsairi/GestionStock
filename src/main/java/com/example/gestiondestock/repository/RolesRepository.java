package com.example.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestiondestock.model.Roles;
import com.google.common.base.Optional;

public interface RolesRepository extends JpaRepository <Roles ,Integer > {

	Roles findByName(String name);
}
