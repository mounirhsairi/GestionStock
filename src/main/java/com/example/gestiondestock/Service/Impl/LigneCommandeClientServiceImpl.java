/*package com.example.gestiondestock.Service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestiondestock.DTO.ArticleDto;
import com.example.gestiondestock.DTO.LigneCommandeClientDto;
import com.example.gestiondestock.Service.LigneCommandeClientService;
import com.example.gestiondestock.exception.EntityNotFoundException;
import com.example.gestiondestock.exception.ErrorCodes;
import com.example.gestiondestock.exception.InvalidEntityException;
import com.example.gestiondestock.model.Article;
import com.example.gestiondestock.model.LigneCommandeClient;
import com.example.gestiondestock.repository.LigneCommandeClientRepository;
import com.example.gestiondestock.validator.ArticleValidator;
import com.example.gestiondestock.validator.LigneCommandeClientValidator;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class LigneCommandeClientServiceImpl implements LigneCommandeClientService {

	private LigneCommandeClientRepository ligneCommandeClientRepository ;
	@Autowired
	public LigneCommandeClientServiceImpl(LigneCommandeClientRepository ligneCommandeClientRepository) {
		this.ligneCommandeClientRepository = ligneCommandeClientRepository;
	}

	@Override
	public LigneCommandeClientDto save(LigneCommandeClientDto dto) {
		List<String> errors =LigneCommandeClientValidator.validate(dto);
		if(!errors.isEmpty()) {
			log.error("ligne commande client is not valid");
			throw new InvalidEntityException("ligne commande client n'est pas valide ",ErrorCodes.LIGNE_COMMANDE_CLIENT_NOT_VALID,errors);
		}
		return LigneCommandeClientDto.fromEntity(ligneCommandeClientRepository.save(LigneCommandeClientDto.toEntity(dto)));
	}

	@Override
	public LigneCommandeClientDto findById(Integer id) {

		if(id == null)
		{
			log.error("ligne commande client Id is null");
			return null;
		}
		Optional<LigneCommandeClient> ligneCommandeClient =ligneCommandeClientRepository.findById(id) ;
		return Optional.of(LigneCommandeClientDto.fromEntity(ligneCommandeClient.get())).orElseThrow(()-> new EntityNotFoundException("aucune ligne commande client  avec l'id "+ id,ErrorCodes.LIGNE_COMMANDE_CLIENT_NOT_FOUND));
	}

	@Override
	public List<LigneCommandeClientDto> findAll() {
		// TODO Auto-generated method stub
		return ligneCommandeClientRepository.findAll().stream().map(LigneCommandeClientDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		if(id == null) {
			log.error("Ligne commande client Id is null");
				return ;
		}		
		 ligneCommandeClientRepository.deleteById(id);;
	}

}*/
