package com.example.gestiondestock.Service;

import java.util.List;

import com.example.gestiondestock.DTO.LigneVenteDto;
import com.example.gestiondestock.DTO.VentesDto;

public interface VentesService {
	VentesDto save(VentesDto dto);
	VentesDto findById(Integer id);
	List<VentesDto> findAll();
	List<LigneVenteDto>findAllLigneVenteByVentesId(Integer idVentes);
	List<LigneVenteDto> findAllLigneVenteByArticleId(Integer idArticle);
	void delete(Integer id);
}
