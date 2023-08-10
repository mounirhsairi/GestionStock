package com.example.gestiondestock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestiondestock.DTO.ClientDto;
import com.example.gestiondestock.Service.ClientService;
import com.example.gestiondestock.controller.api.ClientApi;
@RestController
public class ClientController implements ClientApi {

	private ClientService clientService ;
	@Autowired
	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@Override
	public ClientDto save(ClientDto dto) {
		// TODO Auto-generated method stub
		return clientService.save(dto);
	}

	@Override
	public ClientDto findById(Integer id) {
		// TODO Auto-generated method stub
		return clientService.findById(id);
	}

	@Override
	public List<ClientDto> findAll() {
		// TODO Auto-generated method stub
		return clientService.findAll();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		clientService.delete(id);
	}
	
	
}
