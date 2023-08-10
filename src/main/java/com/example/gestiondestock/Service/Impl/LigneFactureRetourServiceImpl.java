package com.example.gestiondestock.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.gestiondestock.DTO.LigneFactureRetourDto;
import com.example.gestiondestock.Service.LigneFactureRetourService;
import com.example.gestiondestock.repository.LigneFactureRetourRepository;

public class LigneFactureRetourServiceImpl implements LigneFactureRetourService {
	  private final LigneFactureRetourRepository ligneFactureRetourRepository;

	    @Autowired
	    public LigneFactureRetourServiceImpl(LigneFactureRetourRepository ligneFactureRetourRepository) {
			this.ligneFactureRetourRepository = ligneFactureRetourRepository;
		}
	    
	    public LigneFactureRetourDto saveLigneFactureRetour(LigneFactureRetourDto ligneFactureRetour) {
	        return LigneFactureRetourDto.fromEntity(ligneFactureRetourRepository.save(LigneFactureRetourDto.toEntity(ligneFactureRetour))) ;
	    }

	    

		public LigneFactureRetourDto getLigneFactureRetourById(Integer id) {
	        return ligneFactureRetourRepository.findById(id).map(LigneFactureRetourDto::fromEntity).orElse(null);
	    }

	    public List<LigneFactureRetourDto> getAllLigneFactureRetours() {
	        return ligneFactureRetourRepository.findAll().stream().map(LigneFactureRetourDto::fromEntity).collect(Collectors.toList());
	    }

	    public void deleteLigneFactureRetour(Integer id) {
	        ligneFactureRetourRepository.deleteById(id);
	    }

	    // Add other methods as per your requirements
	}
	


