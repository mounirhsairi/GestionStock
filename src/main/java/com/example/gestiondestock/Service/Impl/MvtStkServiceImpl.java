package com.example.gestiondestock.Service.Impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestiondestock.DTO.MvnStockDto;
import com.example.gestiondestock.Service.ArticleService;
import com.example.gestiondestock.Service.MvStkService;
import com.example.gestiondestock.exception.ErrorCodes;
import com.example.gestiondestock.exception.InvalidEntityException;
import com.example.gestiondestock.model.TypeMvtStock;
import com.example.gestiondestock.repository.MvtStockRepository;
import com.example.gestiondestock.validator.MvnStockValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MvtStkServiceImpl implements MvStkService {

	private MvtStockRepository mvtStkRepository ;
	private ArticleService articleService ;
	

	@Autowired
	public MvtStkServiceImpl(MvtStockRepository mvtStkRepository, ArticleService articleService) {
		this.mvtStkRepository = mvtStkRepository;
		this.articleService = articleService;
	}

	@Override
	public BigDecimal stockReelArticle(Integer IdArticle) {
		if(IdArticle == null) {
			log.warn("Id article is null");
			return BigDecimal.valueOf(-1);
		}
		articleService.findById(IdArticle);
		return mvtStkRepository.stockReelArticle(IdArticle);
	}

	@Override
	public List<MvnStockDto> mvtStkArticle(Integer idArticle) {
		
		return mvtStkRepository.findAllByArticleId(idArticle).stream().map(MvnStockDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public MvnStockDto entrerStock(MvnStockDto dto) {

		List<String>errors=MvnStockValidator.validate(dto);
		if(!errors.isEmpty()) {
			log.error("Article is not valid");
			throw new InvalidEntityException("le mounvement de stock n'est pas valide ",ErrorCodes.MVNT_STOCK_INVALID,errors);
		}
		dto.setQuantite(BigDecimal.valueOf(Math.abs(dto.getQuantite().doubleValue())));
		dto.setTypeMvt(TypeMvtStock.ENTREE);
		return MvnStockDto.fromEntity(mvtStkRepository.save(MvnStockDto.toEntity(dto)));
	}

	@Override
	public MvnStockDto sortieStock(MvnStockDto dto) {
		List<String>errors=MvnStockValidator.validate(dto);
		if(!errors.isEmpty()) {
			log.error("Article is not valid");
			throw new InvalidEntityException("le mounvement de stock n'est pas valide ",ErrorCodes.MVNT_STOCK_INVALID,errors);
		}
		dto.setQuantite(BigDecimal.valueOf(Math.abs(dto.getQuantite().doubleValue())* -1));
		dto.setTypeMvt(TypeMvtStock.SORTIE);
		return MvnStockDto.fromEntity(mvtStkRepository.save(MvnStockDto.toEntity(dto)));
	}


	@Override
	public MvnStockDto correctionStockPos(MvnStockDto dto) {
		List<String>errors=MvnStockValidator.validate(dto);
		if(!errors.isEmpty()) {
			log.error("Article is not valid");
			throw new InvalidEntityException("le mounvement de stock n'est pas valide ",ErrorCodes.MVNT_STOCK_INVALID,errors);
		}
		dto.setQuantite(BigDecimal.valueOf(Math.abs(dto.getQuantite().doubleValue())));
		dto.setTypeMvt(TypeMvtStock.CORRECTIN_POS);
		return MvnStockDto.fromEntity(mvtStkRepository.save(MvnStockDto.toEntity(dto)));
	}


	@Override
	public MvnStockDto correctionStockNeg(MvnStockDto dto) {
		List<String>errors=MvnStockValidator.validate(dto);
		if(!errors.isEmpty()) {
			log.error("Article is not valid");
			throw new InvalidEntityException("le mounvement de stock n'est pas valide ",ErrorCodes.MVNT_STOCK_INVALID,errors);
		}
		dto.setQuantite(BigDecimal.valueOf(Math.abs(dto.getQuantite().doubleValue())* -1));
		dto.setTypeMvt(TypeMvtStock.CORRECTION_NEG);
		return MvnStockDto.fromEntity(mvtStkRepository.save(MvnStockDto.toEntity(dto)));
	}

}
