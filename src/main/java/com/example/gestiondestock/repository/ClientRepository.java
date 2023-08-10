package com.example.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestiondestock.model.Client;

public interface ClientRepository  extends JpaRepository< Client ,Integer >{

}
