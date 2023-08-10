package com.example.gestiondestock.Service.Impl;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestiondestock.DTO.UtilisateurDto;
import com.example.gestiondestock.Service.UtilisateurService;
import com.example.gestiondestock.exception.EntityNotFoundException;
import com.example.gestiondestock.exception.ErrorCodes;
import com.example.gestiondestock.exception.InvalidEntityException;
import com.example.gestiondestock.model.User;
import com.example.gestiondestock.repository.UserRepository;
import com.example.gestiondestock.validator.UtilisateurValidator;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService{

	UserRepository utilisateurRepository ;
	@Autowired
	public UtilisateurServiceImpl(UserRepository utilisateurRepository) {
		this.utilisateurRepository = utilisateurRepository;
	}

	@Override
	public UtilisateurDto save(UtilisateurDto dto) {
		List<String> errors = UtilisateurValidator.validate(dto);
		if(!errors.isEmpty()) {
			log.error("Category is not valid",dto);
			throw new InvalidEntityException("l'utilisateur n'est pas valide ",ErrorCodes.UTILISATEUR_NOT_VALID,errors);
		}
	return UtilisateurDto.fromEntity(utilisateurRepository.save(UtilisateurDto.toEntity(dto)));
}

	@Override
	public UtilisateurDto findById(Integer id) {
		if(id == null) {
			log.error("Category Id is null");
				return null;
		}
			Optional<User> utilisateur =utilisateurRepository.findById(id) ;
			return Optional.of(UtilisateurDto.fromEntity(utilisateur.get())).orElseThrow(()-> new EntityNotFoundException("aucune categorie avec le code "+ id,ErrorCodes.UTILISATEUR_NOT_FOUND));
		}
		
	@Override
	public List<UtilisateurDto> findAll() {
		// TODO Auto-generated method stub
		return utilisateurRepository.findAll().stream().map(UtilisateurDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		if(id == null) {
			log.error("utilisateur Id is null");
				return ;
		}
		utilisateurRepository.deleteById(id);
		
	}

	@Override
	public List<UtilisateurDto> findAllByEntrepriseId(Integer idEntreprise) {
		if(idEntreprise== null)
		{
			 throw new EntityNotFoundException("aucune entreprise avec l'id"+ idEntreprise,ErrorCodes.ENTREPRISE_NOT_FOUND);
		}
		
		return utilisateurRepository.findAllByEntrepriseId(idEntreprise).stream().map(UtilisateurDto::fromEntity).collect(Collectors.toList());
	}		
	}
	

