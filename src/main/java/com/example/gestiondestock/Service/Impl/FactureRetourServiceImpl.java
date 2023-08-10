package com.example.gestiondestock.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestiondestock.DTO.FactureClientDto;
import com.example.gestiondestock.DTO.FactureRetourDto;
import com.example.gestiondestock.Service.FactureRetourService;
import com.example.gestiondestock.exception.EntityNotFoundException;
import com.example.gestiondestock.exception.ErrorCodes;
import com.example.gestiondestock.repository.FactureRetourRepository;
@Service
public class FactureRetourServiceImpl implements FactureRetourService {

	 private final FactureRetourRepository factureRetourRepository;

	    @Autowired
	    public FactureRetourServiceImpl(FactureRetourRepository factureRetourRepository) {
	        this.factureRetourRepository = factureRetourRepository;
	    }

		@Override
		public FactureRetourDto saveFactureRetour(FactureRetourDto factureRetour) {
			// TODO Auto-generated method stub
	    return 	FactureRetourDto.fromEntity(factureRetourRepository.save(FactureRetourDto.toEntity(factureRetour)))    ;
		}

		@Override
		public FactureRetourDto getFactureRetourById(Integer id) {
			// TODO Auto-generated method stub
			return factureRetourRepository.findById(id)
	                .map(FactureRetourDto::fromEntity)
	                .orElseThrow(() -> new EntityNotFoundException(
	                        "Aucune facture retour n'a été trouvée avec l'ID " + id,
	                        ErrorCodes.FACTURE_RETOUR_NOT_FOUND));		}

		@Override
		public List<FactureRetourDto> getAllFactureRetours() {
			// TODO Auto-generated method stub
			return factureRetourRepository.findAll().stream().map(FactureRetourDto::fromEntity).collect(Collectors.toList());
		}

		@Override
		public void deleteFactureRetour(Integer id) {
			factureRetourRepository.deleteById(id);			
		}
	
	  

}
