package com.example.gestiondestock.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestiondestock.model.User;


public interface UserRepository extends JpaRepository <User ,Integer >{

	List<User> findAllByEntrepriseId(Integer idEntreprise);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
