package com.example.gestiondestock.Service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestiondestock.DTO.FournisseurDto;
import com.example.gestiondestock.Service.FournisseurService;
import com.example.gestiondestock.exception.EntityNotFoundException;
import com.example.gestiondestock.exception.ErrorCodes;
import com.example.gestiondestock.exception.InvalidEntityException;
import com.example.gestiondestock.exception.InvalidOperationException;
import com.example.gestiondestock.model.CommandeClient;
import com.example.gestiondestock.model.CommandeFournisseur;
import com.example.gestiondestock.model.Fournisseur;
import com.example.gestiondestock.repository.CommandeFournisseurRepository;
import com.example.gestiondestock.repository.FournisseurRepository;
import com.example.gestiondestock.validator.FournisseurValidator;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

	private FournisseurRepository fournisseurRepository ;
	private CommandeFournisseurRepository commandeFournisseurRepository ;
	@Autowired
	public FournisseurServiceImpl(FournisseurRepository fournisseurRepository ,CommandeFournisseurRepository commandeFournisseurRepository) {
		this.fournisseurRepository = fournisseurRepository;
		this.commandeFournisseurRepository =commandeFournisseurRepository;
	}

	@Override
	public FournisseurDto save(FournisseurDto dto) {
		List<String> errors = FournisseurValidator.validate(dto);
		if(!errors.isEmpty()) {
			log.error("Fournisseur is not valid",dto);
			throw new InvalidEntityException("le fournisseur n'est pas valide ",ErrorCodes.FOURNISSEUR_NOT_VALID,errors);
		}
	return FournisseurDto.fromEntity(fournisseurRepository.save(FournisseurDto.toEntity(dto)));
	}

	@Override
	public FournisseurDto findById(Integer id) {
		if(id == null) {
			log.error("fournisseur Id is null");
				return null;
		}
			Optional<Fournisseur> fournisseur =fournisseurRepository.findById(id) ;
			return Optional.of(FournisseurDto.fromEntity(fournisseur.get())).orElseThrow(()-> new EntityNotFoundException("aucun fournisseur avec le code "+ id,ErrorCodes.FOURNISSEUR_NOT_FOUND));
		}
	
	@Override
	public List<FournisseurDto> findAll() {
		// TODO Auto-generated method stub
		return fournisseurRepository.findAll().stream().map(FournisseurDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		if(id == null) {
			log.error("fournisseur Id is null");
				return ;
		}
		List<CommandeFournisseur> CommandesFournisseur =	commandeFournisseurRepository.findAllByFournisseurId(id);
		if(!CommandesFournisseur.isEmpty())
		{
			throw new InvalidOperationException("impossible de supprimer un fournisseur qui est deja des commandes fournisseur ",ErrorCodes.FOURNISSEUR_ALLREADY_IN_USE);

		}
		fournisseurRepository.deleteById(id);
		
	}

	}


