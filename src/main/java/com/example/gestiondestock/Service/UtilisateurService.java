package com.example.gestiondestock.Service;

import java.util.List;

import com.example.gestiondestock.DTO.UtilisateurDto;

public interface UtilisateurService {
	UtilisateurDto save(UtilisateurDto dto);
	UtilisateurDto findById(Integer id);
	List<UtilisateurDto> findAll();
	List<UtilisateurDto> findAllByEntrepriseId(Integer idEntreprise);
	void delete(Integer id);

}
