package com.example.gestiondestock.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestiondestock.DTO.MvnStockDto;
import com.example.gestiondestock.Service.MvStkService;
import com.example.gestiondestock.controller.api.MvtStkApi;

@RestController
public class MvtStkController implements MvtStkApi{

	private MvStkService service;
	@Autowired
	public MvtStkController(MvStkService service) {
		
		this.service = service;
	}

	@Override
	public BigDecimal stockReelArticle(Integer idArticle) {
		// TODO Auto-generated method stub
		return service.stockReelArticle(idArticle);
	}

	@Override
	public List<MvnStockDto> mvtStkArticle(Integer idArticle) {
		// TODO Auto-generated method stub
		return service.mvtStkArticle(idArticle);
	}

	@Override
	public MvnStockDto entrerStock(MvnStockDto dto) {
		// TODO Auto-generated method stub
		return service.entrerStock(dto);
	}

	@Override
	public MvnStockDto sortieStock(MvnStockDto dto) {
		// TODO Auto-generated method stub
		return service.sortieStock(dto);
	}

	@Override
	public MvnStockDto correctionStockPos(MvnStockDto dto) {
		// TODO Auto-generated method stub
		return service.correctionStockPos(dto);
	}

	@Override
	public MvnStockDto correctionStockNeg(MvnStockDto dto) {
		// TODO Auto-generated method stub
		return service.correctionStockNeg(dto);
	}

}
