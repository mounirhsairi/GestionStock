package com.example.gestiondestock.Service;

import java.util.List;

import com.example.gestiondestock.DTO.LigneFactureRetourDto;

public interface LigneFactureRetourService {
	
	public LigneFactureRetourDto saveLigneFactureRetour(LigneFactureRetourDto ligneFactureRetour);
	public LigneFactureRetourDto getLigneFactureRetourById(Integer id) ;
	public List<LigneFactureRetourDto> getAllLigneFactureRetours();
	 public void deleteLigneFactureRetour(Integer id);
}
