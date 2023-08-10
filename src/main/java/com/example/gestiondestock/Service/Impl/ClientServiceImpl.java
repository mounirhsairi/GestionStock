package com.example.gestiondestock.Service.Impl;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.gestiondestock.model.CommandeClient;
import com.example.gestiondestock.DTO.ClientDto;
import com.example.gestiondestock.Service.ClientService;
import com.example.gestiondestock.exception.EntityNotFoundException;
import com.example.gestiondestock.exception.ErrorCodes;
import com.example.gestiondestock.exception.InvalidEntityException;
import com.example.gestiondestock.exception.InvalidOperationException;
import com.example.gestiondestock.model.Client;
import com.example.gestiondestock.repository.ClientRepository;
import com.example.gestiondestock.repository.CommandeClientRepository;
import com.example.gestiondestock.validator.ClientValidator;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository ;
    private CommandeClientRepository commandeClientRepository ;
    @Autowired
	public ClientServiceImpl(ClientRepository clientRepository ,CommandeClientRepository commandeClientRepository) {
		this.clientRepository = clientRepository;
		this.commandeClientRepository= commandeClientRepository;
	}

	@Override
	public ClientDto save(ClientDto dto) {
		List<String> errors = ClientValidator.validate(dto);
		if(!errors.isEmpty()) {
			log.error("Client is not valid",dto);
			throw new InvalidEntityException("le client n'est pas valide ",ErrorCodes.CLIENT_NOT_VALID,errors);
		}
	return ClientDto.fromEntity(clientRepository.save(ClientDto.toEntity(dto)));	}

	@Override
	public ClientDto findById(Integer id) {
		// TODO Auto-generated method stub
		if(id == null) {
			log.error("Client Id is null");
				return null;
		}
			Optional<Client> client =clientRepository.findById(id) ;
			return Optional.of(ClientDto.fromEntity(client.get())).orElseThrow(()-> new EntityNotFoundException("aucun client avec le code "+ id,ErrorCodes.CLIENT_NOT_FOUND));
		}

	@Override
	public List<ClientDto> findAll() {
		// TODO Auto-generated method stub
		return clientRepository.findAll().stream().map(ClientDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		if(id == null) {
			log.error("Client Id is null");
				return ;
		}
		List<CommandeClient> CommandesClient =	commandeClientRepository.findAllByClientId(id);
		if(!CommandesClient.isEmpty())
		{
			throw new InvalidOperationException("impossible de supprimer un client qui est deja des commandes client ",ErrorCodes.CLIENT_ALLREADY_IN_USE);

		}
			clientRepository.deleteById(id);
		
	}
}


