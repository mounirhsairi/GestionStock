package com.example.gestiondestock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestiondestock.DTO.FactureFournisseurDto;
import com.example.gestiondestock.Service.FactureFournisseurService;
import com.example.gestiondestock.controller.api.FactureFournisseurApi;
import com.example.gestiondestock.model.ArticleDetails;
import com.example.gestiondestock.model.FactureFournisseur;
@RestController
public class FactureFournisseurController implements FactureFournisseurApi {

	private FactureFournisseurService factureFournisseurService ;
	
	@Autowired
	public FactureFournisseurController(FactureFournisseurService factureFournisseurService) {
		this.factureFournisseurService = factureFournisseurService;
	}

	@Override
	public FactureFournisseurDto createFactureFournisseur(Integer id) {
		// TODO Auto-generated method stub
		return factureFournisseurService.createFactureFournisseur(id);
	}

	@Override
	public List<FactureFournisseurDto> getAllFactureFournisseurs() {
		// TODO Auto-generated method stub
		return factureFournisseurService.getAllFactureFournisseurs();
	}

	@Override
	public FactureFournisseurDto getFactureFournisseurById(Integer id) {
		// TODO Auto-generated method stub
		return factureFournisseurService.getFactureFournisseurById(id);
	}

	@Override
	public FactureFournisseurDto updateFactureFournisseur(Integer id, FactureFournisseurDto factureFournisseurDto) {
		// TODO Auto-generated method stub
		return factureFournisseurService.updateFactureFournisseur(id, factureFournisseurDto);
	}

	@Override
	public void deleteFactureFournisseur(Integer id) {
		// TODO Auto-generated method stub
		factureFournisseurService.deleteFactureFournisseur(id);;
	}

	@Override
	public double calculateMontantFactureFournisseur(Integer id) {
		// TODO Auto-generated method stub
		return factureFournisseurService.calculateMontantFactureFournisseur(id);
	}
	@Override
	public List<ArticleDetails> getArticlesAndPrixQuantite(Integer id) {
		 FactureFournisseur factureFournisseur =FactureFournisseurDto.toEntity(factureFournisseurService.getFactureFournisseurById(id)) ;

	        return  factureFournisseurService.getArticlesAndPrixQuantite(factureFournisseur);
	}

}
