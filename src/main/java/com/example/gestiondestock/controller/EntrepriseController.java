package com.example.gestiondestock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestiondestock.DTO.EntrepriseDto;
import com.example.gestiondestock.Service.EntrepriseService;
import com.example.gestiondestock.controller.api.EntrepriseApi;

@RestController
public class EntrepriseController implements EntrepriseApi {
	private EntrepriseService entrepriseService ;
	@Autowired
	public EntrepriseController(EntrepriseService entrepriseService) {
		super();
		this.entrepriseService = entrepriseService;
	}
	@Override
	public EntrepriseDto save(EntrepriseDto dto) {
		// TODO Auto-generated method stub
		return entrepriseService.save(dto);
	}
	@Override
	public EntrepriseDto findById(Integer id) {
		// TODO Auto-generated method stub
		return entrepriseService.findById(id);
	}
	
	@Override
	public List<EntrepriseDto> findAll() {
		// TODO Auto-generated method stub
		return entrepriseService.findAll();
	}
	@Override
	public void delete(Integer id) {
		
		entrepriseService.delete(id);		
	}
	@Override
	public EntrepriseDto findByCodeEntreprise(String code) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
