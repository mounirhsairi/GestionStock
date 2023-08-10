package com.example.gestiondestock.controller.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.gestiondestock.DTO.FactureRetourDto;
import com.example.gestiondestock.utils.Constants;

import io.swagger.annotations.Api;
@Api(Constants.APP_ROOT+"/facturesRetour")

public interface FactureRetourApi {
	 @PostMapping(value = Constants.APP_ROOT + "/facturesRetour/Create")
	 public ResponseEntity<FactureRetourDto> addFactureRetour(@RequestBody FactureRetourDto factureRetour);
	 @GetMapping(value = Constants.APP_ROOT + "/facturesRetour/{id}",produces = "application/json")
	 public ResponseEntity<FactureRetourDto> getFactureRetourById(@PathVariable Integer id) ;
	 @GetMapping(value = Constants.APP_ROOT + "/facturesRetour/all",produces = "application/json")
	 public ResponseEntity<List<FactureRetourDto>> getAllFactureRetours();
	    
	@DeleteMapping(value = Constants.APP_ROOT + "/facturesRetour/delete/{id}")
	public ResponseEntity<Void> deleteFactureRetour(@PathVariable Integer id);
}
