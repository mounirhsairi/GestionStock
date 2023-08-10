/*package com.example.gestiondestock.Service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestiondestock.DTO.ArticleDto;
import com.example.gestiondestock.DTO.LigneCommandeFournisseurDto;
import com.example.gestiondestock.Service.LigneCommandeFournisseurService;
import com.example.gestiondestock.exception.EntityNotFoundException;
import com.example.gestiondestock.exception.ErrorCodes;
import com.example.gestiondestock.exception.InvalidEntityException;
import com.example.gestiondestock.model.Article;
import com.example.gestiondestock.model.LigneCommandeFournisseur;
import com.example.gestiondestock.repository.LigneCommandeFournisseurRepository;
import com.example.gestiondestock.validator.ArticleValidator;
import com.example.gestiondestock.validator.LigneCommandeFournisseurValidator;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class LigneCommandeFournisseurServiceImpl implements LigneCommandeFournisseurService {

	private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository ;
	@Autowired
	public LigneCommandeFournisseurServiceImpl(LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository) {
		this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
	}

	@Override
	public LigneCommandeFournisseurDto save(LigneCommandeFournisseurDto dto) {
		List<String> errors =LigneCommandeFournisseurValidator.validate(dto);
		if(!errors.isEmpty()) {
			log.error("ligne commande Fournisseur is not valid");
			throw new InvalidEntityException("ligne commande Fournisseur n'est pas valide ",ErrorCodes.LIGNE_COMMANDE_FOURNISSEUR_NOT_VALID,errors);
		}
		return LigneCommandeFournisseurDto.fromEntity(ligneCommandeFournisseurRepository.save(LigneCommandeFournisseurDto.toEntity(dto)));
	}

	@Override
	public LigneCommandeFournisseurDto findById(Integer id) {

		if(id == null)
		{
			log.error("ligne commande Fournisseur Id is null");
			return null;
		}
		Optional<LigneCommandeFournisseur> ligneCommandeFournisseur =ligneCommandeFournisseurRepository.findById(id) ;
		return Optional.of(LigneCommandeFournisseurDto.fromEntity(ligneCommandeFournisseur.get())).orElseThrow(()-> new EntityNotFoundException("aucune ligne commande Fournisseur  avec l'id "+ id,ErrorCodes.LIGNE_COMMANDE_FOURNISSEUR_NOT_FOUND));
	}

	@Override
	public List<LigneCommandeFournisseurDto> findAll() {
		// TODO Auto-generated method stub
		return ligneCommandeFournisseurRepository.findAll().stream().map(LigneCommandeFournisseurDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		if(id == null) {
			log.error("Ligne commande Fournisseur Id is null");
				return ;
		}		
		 ligneCommandeFournisseurRepository.deleteById(id);;
	}

}
*/