package com.example.gestiondestock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestiondestock.DTO.FactureRetourDto;
import com.example.gestiondestock.Service.FactureRetourService;
import com.example.gestiondestock.controller.api.FactureRetourApi;

@RestController

public class FactureRetourController implements FactureRetourApi {

	 private final FactureRetourService factureRetourService;

	    @Autowired
	    public FactureRetourController(FactureRetourService factureRetourService) {
	        this.factureRetourService = factureRetourService;
	    }

	@Override
	public ResponseEntity<FactureRetourDto> addFactureRetour(FactureRetourDto factureRetour) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(factureRetourService.saveFactureRetour(factureRetour));
	}

	@Override
	public ResponseEntity<FactureRetourDto> getFactureRetourById(Integer id) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(factureRetourService.getFactureRetourById(id));
	}

	@Override
	public ResponseEntity<List<FactureRetourDto>> getAllFactureRetours() {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(factureRetourService.getAllFactureRetours());
	}

	@Override
	public ResponseEntity<Void> deleteFactureRetour(Integer id) {
		// TODO Auto-generated method stub
		factureRetourService.deleteFactureRetour(id);
		return ResponseEntity.ok().build();	}
	}


