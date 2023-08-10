package com.example.gestiondestock.Service;

import java.util.List;


import com.example.gestiondestock.DTO.FournisseurDto;

public interface FournisseurService {
	FournisseurDto save(FournisseurDto dto);
	FournisseurDto findById(Integer id);
	List<FournisseurDto> findAll();
	void delete(Integer id);
	

}
