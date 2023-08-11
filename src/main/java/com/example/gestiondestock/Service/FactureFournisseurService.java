package com.example.gestiondestock.Service;

import java.util.List;




import com.example.gestiondestock.DTO.FactureFournisseurDto;
import com.example.gestiondestock.model.ArticleDetails;
import com.example.gestiondestock.model.FactureFournisseur;

public interface FactureFournisseurService {
    public List<FactureFournisseurDto> getAllFactureFournisseurs() ;
    public FactureFournisseurDto getFactureFournisseurById(Integer id) ;
    public FactureFournisseurDto createFactureFournisseur(Integer idCommande) ;
    public FactureFournisseurDto updateFactureFournisseur(Integer id, FactureFournisseurDto factureFournisseur) ;
    public void deleteFactureFournisseur(Integer id) ;
	public double calculateMontantFactureFournisseur(Integer id);
    public List<ArticleDetails> getArticlesAndPrixQuantite(FactureFournisseur factureFournisseur);

}
