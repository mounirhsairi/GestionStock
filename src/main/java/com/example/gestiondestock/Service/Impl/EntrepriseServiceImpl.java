package com.example.gestiondestock.Service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestiondestock.DTO.EntrepriseDto;
import com.example.gestiondestock.Service.EntrepriseService;
import com.example.gestiondestock.exception.EntityNotFoundException;
import com.example.gestiondestock.exception.ErrorCodes;
import com.example.gestiondestock.exception.InvalidEntityException;
import com.example.gestiondestock.model.Entreprise;
import com.example.gestiondestock.repository.EntrepriseRepository;
import com.example.gestiondestock.validator.EntrepriseValidator;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {
	
	private EntrepriseRepository entrepriseRepository ;
	@Autowired
	public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
		
		this.entrepriseRepository = entrepriseRepository;
	}

	@Override
	public EntrepriseDto save(EntrepriseDto dto) {
		List<String> errors = EntrepriseValidator.validate(dto);
		if(!errors.isEmpty()) {
			log.error("Entreprise is not valid",dto);
			throw new InvalidEntityException("l'entreprise n'est pas valide ",ErrorCodes.ENTREPRISE_NOT_VALID,errors);
		}
	return EntrepriseDto.fromEntity(entrepriseRepository.save(EntrepriseDto.toEntity(dto)));
	
}
	


	@Override
	public EntrepriseDto findById(Integer id) {
		if(id == null) {
			log.error("Entreprise Id is null");
				return null;
		}
			Optional<Entreprise> entreprise =entrepriseRepository.findById(id) ;
			return Optional.of(EntrepriseDto.fromEntity(entreprise.get())).orElseThrow(()-> new EntityNotFoundException("aucune entreprise avec le code "+ id,ErrorCodes.ENTREPRISE_NOT_FOUND));
		}
	

	

	@Override
	public List<EntrepriseDto> findAll() {
		// TODO Auto-generated method stub
		return entrepriseRepository.findAll().stream().map(EntrepriseDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		if(id == null) {
			log.error("entreprise Id is null");
				return ;
		}
			entrepriseRepository.deleteById(id);
		
	}

	}

