package com.example.gestiondestock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestiondestock.DTO.ArticleDto;
import com.example.gestiondestock.DTO.UtilisateurDto;
import com.example.gestiondestock.Service.UtilisateurService;
import com.example.gestiondestock.controller.api.UtilisateurApi;

@RestController
public class UtilisateurController implements UtilisateurApi {
 
	private UtilisateurService utilisateurService;
	
	@Autowired
	public UtilisateurController(UtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService;
	}

	@Override
	public UtilisateurDto save(UtilisateurDto dto) {
		return utilisateurService.save(dto);
	}

	@Override
	public UtilisateurDto findById(Integer id) {
		return utilisateurService.findById(id);
	}

	@Override
	public List<UtilisateurDto> findAll() {
		return utilisateurService.findAll();
	}

	@Override
	public void delete(Integer id) {
		utilisateurService.delete(id);
	}

	@Override
	public List<UtilisateurDto> findAllByEntrepriseId(Integer idEntreprise) {
		// TODO Auto-generated method stub
		return utilisateurService.findAllByEntrepriseId(idEntreprise);
	}	
}
