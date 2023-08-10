package com.example.gestiondestock.Service;

import java.util.List;

import com.example.gestiondestock.DTO.FactureClientDto;
import com.example.gestiondestock.model.ArticleDetails;
import com.example.gestiondestock.model.FactureClient;

public interface FactureClientService {
	     List<FactureClientDto> getAllFactureClients() ;
	     FactureClientDto getFactureClientById(Integer id) ;
	     FactureClientDto createFactureClient(FactureClientDto factureClient) ;
	     FactureClientDto updateFactureClient(Integer id, FactureClientDto factureClient) ;
	     void delete(Integer id) ;
	     double calculateMontantFactureClient(Integer id) ;
	     public List<ArticleDetails> getArticlesAndPrixQuantite(FactureClient factureClient);
}
