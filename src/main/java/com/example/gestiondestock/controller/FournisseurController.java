package com.example.gestiondestock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestiondestock.DTO.FournisseurDto;
import com.example.gestiondestock.Service.FournisseurService;
import com.example.gestiondestock.controller.api.FournisseurApi;

@RestController
public class FournisseurController implements FournisseurApi {

	private FournisseurService fournisseurService ;
	@Autowired
	public FournisseurController(FournisseurService fournisseurService) {
		super();
		this.fournisseurService = fournisseurService;
	}

	@Override
	public FournisseurDto save(FournisseurDto dto) {
		// TODO Auto-generated method stub
		return fournisseurService.save(dto);
	}

	@Override
	public FournisseurDto findById(Integer id) {
		// TODO Auto-generated method stub
		return fournisseurService.findById(id);
	}

	

	@Override
	public List<FournisseurDto> findAll() {
		// TODO Auto-generated method stub
		return fournisseurService.findAll();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		fournisseurService.delete(id);
	}

	
	
}
