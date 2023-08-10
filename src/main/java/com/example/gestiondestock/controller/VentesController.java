package com.example.gestiondestock.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestiondestock.DTO.LigneVenteDto;
import com.example.gestiondestock.DTO.VentesDto;
import com.example.gestiondestock.Service.VentesService;
import com.example.gestiondestock.controller.api.VentesApi;
@RestController
public class VentesController implements VentesApi {

	private VentesService ventesService ;
	
	@Autowired
	public VentesController(VentesService ventesService) {
		this.ventesService = ventesService;
	}


	@Override
	public ResponseEntity<VentesDto> save(VentesDto dto) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(ventesService.save(dto));
	}


	@Override
	public ResponseEntity<VentesDto> findById(Integer idVentes) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(ventesService.findById(idVentes));
	}


	@Override
	public ResponseEntity<List<VentesDto>> findAll() {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(ventesService.findAll());
	}


	@Override
	public ResponseEntity delete(Integer idVentes) {
		// TODO Auto-generated method stub
		ventesService.delete(idVentes);
		return ResponseEntity.ok().build();	}


	@Override
	public ResponseEntity<List<LigneVenteDto>> findAllLigneVenteByVentesId(Integer idVentes) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(ventesService.findAllLigneVenteByVentesId(idVentes));
	}


	@Override
	public ResponseEntity<List<LigneVenteDto>> findAllLigneVenteByArticleId(Integer idArticle) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(ventesService.findAllLigneVenteByArticleId(idArticle));
	}

}
