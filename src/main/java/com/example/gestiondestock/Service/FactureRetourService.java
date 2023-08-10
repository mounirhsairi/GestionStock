package com.example.gestiondestock.Service;

import java.util.List;

import com.example.gestiondestock.DTO.FactureRetourDto;

public interface FactureRetourService {
	public FactureRetourDto saveFactureRetour(FactureRetourDto factureRetour);
	public FactureRetourDto getFactureRetourById(Integer id);
	public List<FactureRetourDto> getAllFactureRetours();
	public void deleteFactureRetour(Integer id);
}
