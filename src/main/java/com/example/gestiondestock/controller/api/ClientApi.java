package com.example.gestiondestock.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.gestiondestock.DTO.ClientDto;
import com.example.gestiondestock.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(Constants.APP_ROOT+"/clients")

public interface ClientApi {
	@PostMapping(value = Constants.APP_ROOT + "/clients/Create", consumes = "application/json", produces = "application/json")
	@ApiOperation(value="enregistrer un article",notes="cette methode permet d'enregistrer ou modifier un client", response=ClientDto.class)
    ClientDto save(@RequestBody ClientDto dto);
	@GetMapping(value = Constants.APP_ROOT + "/clients/{idClient}",produces = "application/json")
	@ApiOperation(value="rechercher un clients par son id",notes="cette methode permet de rechercher un client", response=ClientDto.class)
	ClientDto findById(@PathVariable("idClient") Integer id);
	@GetMapping(value = Constants.APP_ROOT + "/clients/all",produces = "application/json")
	@ApiOperation(value="renvoie la liste des client ",notes="cette methode permet de renvoyer la liste des client ", responseContainer="list<ClientDto>")
	List<ClientDto> findAll();
	@DeleteMapping(value = Constants.APP_ROOT + "/clients/delete/{idClient}")
	@ApiOperation(value="supprimer un client ",notes="cette methode permet de supprimer un client ", response=ClientDto.class)

	void delete(@PathVariable("idClient")Integer id);
}
