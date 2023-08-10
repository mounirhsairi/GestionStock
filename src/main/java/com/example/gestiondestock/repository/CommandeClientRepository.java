package com.example.gestiondestock.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestiondestock.model.CommandeClient;

public interface CommandeClientRepository extends JpaRepository<CommandeClient,Integer>{

	List<CommandeClient> findAllByClientId(Integer id);
}

