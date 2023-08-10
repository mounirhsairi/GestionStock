package com.example.gestiondestock.Service;

import java.util.List;

import com.example.gestiondestock.DTO.ClientDto;

public interface ClientService {
	ClientDto save(ClientDto dto);
	ClientDto findById(Integer id);
	List<ClientDto> findAll();
	void delete(Integer id);
	
}
