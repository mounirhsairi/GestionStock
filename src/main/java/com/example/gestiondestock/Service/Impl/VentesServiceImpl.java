package com.example.gestiondestock.Service.Impl ;

import java.util.List;
import java.time.Instant;
import java.util.ArrayList;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestiondestock.DTO.ArticleDto;
import com.example.gestiondestock.DTO.LigneCommandeClientDto;
import com.example.gestiondestock.DTO.LigneVenteDto;
import com.example.gestiondestock.DTO.MvnStockDto;
import com.example.gestiondestock.DTO.VentesDto;
import com.example.gestiondestock.Service.MvStkService;
import com.example.gestiondestock.Service.VentesService;
import com.example.gestiondestock.exception.EntityNotFoundException;
import com.example.gestiondestock.exception.ErrorCodes;
import com.example.gestiondestock.exception.InvalidEntityException;
import com.example.gestiondestock.exception.InvalidOperationException;
import com.example.gestiondestock.model.Article;
import com.example.gestiondestock.model.CommandeFournisseur;
import com.example.gestiondestock.model.LigneCommandeClient;
import com.example.gestiondestock.model.LigneVente;
import com.example.gestiondestock.model.SourceMvtStock;
import com.example.gestiondestock.model.TypeMvtStock;
import com.example.gestiondestock.model.Ventes;
import com.example.gestiondestock.repository.ArticleRepository;
import com.example.gestiondestock.repository.LigneVenteRepository;
import com.example.gestiondestock.repository.VentesRepository;
import com.example.gestiondestock.validator.VentesValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VentesServiceImpl implements VentesService {
	private ArticleRepository articleRepository ;
	private VentesRepository ventesRepository ;
	private LigneVenteRepository ligneVentesRepository ;
    private MvStkService mvtStkService;

	@Autowired
	public VentesServiceImpl(ArticleRepository articleRepository, VentesRepository ventesRepository,
			LigneVenteRepository ligneVentesRepository ,MvStkService mvtStkService) {
		
		this.articleRepository = articleRepository;
		this.ventesRepository = ventesRepository;
		this.ligneVentesRepository = ligneVentesRepository;
		this.mvtStkService= mvtStkService;
	}

	@Override
	public VentesDto  save(VentesDto dto) {
		List<String> errors = VentesValidator.validate(dto);
		if(!errors.isEmpty())
		{
			log.error("commande n'est pas valide");
			throw new InvalidEntityException("Ventes n'est pas valide",ErrorCodes.VENTES_INVALID);
		}
		List<String>articleErrors = new ArrayList<>();
		dto.getLigneVente().forEach(ligneVenteDto ->{
			Optional<Article> article =articleRepository.findById(ligneVenteDto.getArticle().getId());
		if(article.isEmpty())
		{
			articleErrors.add("Aucun article avec l'ID" +ligneVenteDto.getArticle().getId()+"n'a ete trouve dans laBDD");
		
		}
		});
		if(!articleErrors.isEmpty())
		{
			log.error("one or more articles were notfounds in the DB ",errors);
			throw new InvalidEntityException("one or more articles were notfounds in the DB",ErrorCodes.VENTES_INVALID,errors);
		}
		
		Ventes savedVentes =ventesRepository.save(VentesDto.toEntity(dto)) ;
		dto.getLigneVente().forEach(ligneVenteDto ->{
			LigneVente ligneVente =LigneVenteDto.toEntity(ligneVenteDto);
			ligneVente.setVente(savedVentes);
			ligneVentesRepository.save(ligneVente);
			updateMvtStk(ligneVente);
		});
		return VentesDto.fromEntity(savedVentes);
	}
	

	@Override
	public VentesDto findById(Integer id) {

		if(id == null) {
			log.error("vente ID is null");
			return  null ;
		}
		
		return ventesRepository.findById(id).map(VentesDto::fromEntity).orElseThrow(()->new EntityNotFoundException("Aucune commande client n'a ete trouve avec l'ID"+ id,ErrorCodes.VENTE__NOT_FOUND));
	
	}



	@Override
	public List<VentesDto> findAll() {
		return ventesRepository.findAll().stream().map(VentesDto::fromEntity).collect(Collectors.toList());

	}

	@Override
	public void delete(Integer id) {
		if(id == null) {
			log.error("vente ID is null");
			return   ;
		}
		List<LigneVente> ligneVente =	ligneVentesRepository.findAllByVenteId(id);
		if(!ligneVente.isEmpty())
		{
			throw new InvalidOperationException("impossible de supprimer une vente qui est deja des lignes de vente ",ErrorCodes.VENTE_ALLREADY_IN_USE);

		}
		ventesRepository.deleteById(id);		
	}
	private void updateMvtStk(LigneVente lig) {
		
			MvnStockDto mvtStkDto = MvnStockDto.builder()
					.article(ArticleDto.fromEntity(lig.getArticle()))
					.dateMvt(Instant.now())
					.typeMvt(TypeMvtStock.SORTIE)
					.sourceMvt(SourceMvtStock.VENTE)
					.quantite(lig.getQuantite())
					.idEntreprise(lig.getIdEntreprise())
					.build();
			mvtStkService.sortieStock(mvtStkDto);
		}

	@Override
	public List<LigneVenteDto> findAllLigneVenteByVentesId(Integer idVentes) {
	    return ligneVentesRepository.findAllByVenteId(idVentes)
	            .stream()
	            .map(LigneVenteDto::fromEntity)
	            .collect(Collectors.toList());
	}


	@Override
	public List<LigneVenteDto> findAllLigneVenteByArticleId(Integer idArticle) {

		
		
		return ligneVentesRepository.findAllByArticleId(idArticle).stream().map(LigneVenteDto::fromEntity).collect(Collectors.toList());
	}	
	
}
