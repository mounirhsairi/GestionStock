package com.example.gestiondestock.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestiondestock.DTO.FactureClientDto;
import com.example.gestiondestock.Service.FactureClientService;
import com.example.gestiondestock.controller.api.FactureClientApi;
import com.example.gestiondestock.model.ArticleDetails;
import com.example.gestiondestock.model.FactureClient;
@RestController
public class FactureClientController implements FactureClientApi {
	
	
	private FactureClientService factureClientService ;
	
	@Autowired
	public FactureClientController(FactureClientService factureClientService) {
		this.factureClientService = factureClientService;
	}

	@Override
	public FactureClientDto createFactureClient(Integer idCommande) {
		// TODO Auto-generated method stub
		return factureClientService.createFactureClient(idCommande);
	}

	@Override
	public List<FactureClientDto> getAllFactureClients() {
		// TODO Auto-generated method stub
		return factureClientService.getAllFactureClients();
	}

	@Override
	public FactureClientDto getFactureClientById(Integer id) {
		// TODO Auto-generated method stub
		return factureClientService.getFactureClientById(id);
	}

	@Override
	public FactureClientDto updateFactureClient(Integer id, FactureClientDto factureClientDto) {
		// TODO Auto-generated method stub
		return factureClientService.updateFactureClient(id, factureClientDto);
	}

	@Override
	public void deleteFactureClient(Integer id) {
		// TODO Auto-generated method stub
		factureClientService.delete(id);
	}

	@Override
	public double calculateMontantFactureClient(Integer id) {
		// TODO Auto-generated method stub
		return factureClientService.calculateMontantFactureClient(id);
	}

	@Override
	public List<ArticleDetails> getArticlesAndPrixQuantite(Integer id) {
		 FactureClient factureClient =FactureClientDto.toEntity(factureClientService.getFactureClientById(id)) ;

	        return  factureClientService.getArticlesAndPrixQuantite(factureClient);
	}

	

}
