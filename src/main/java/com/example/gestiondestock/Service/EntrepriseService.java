package com.example.gestiondestock.Service;

import java.util.List;

import com.example.gestiondestock.DTO.EntrepriseDto;

public interface EntrepriseService {
	EntrepriseDto save(EntrepriseDto dto);
	EntrepriseDto findById(Integer id);
	List<EntrepriseDto> findAll();
	void delete(Integer id);
	
}
