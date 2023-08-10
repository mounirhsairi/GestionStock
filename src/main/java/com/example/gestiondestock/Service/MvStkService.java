package com.example.gestiondestock.Service;

import java.math.BigDecimal;
import java.util.List;

import com.example.gestiondestock.DTO.MvnStockDto;
public interface MvStkService {
 
	BigDecimal stockReelArticle(Integer IdArticle);
	List<MvnStockDto> mvtStkArticle(Integer idArticle);
	MvnStockDto entrerStock(MvnStockDto dto);
	MvnStockDto sortieStock(MvnStockDto dto);
	MvnStockDto correctionStockPos(MvnStockDto dto);
	MvnStockDto correctionStockNeg(MvnStockDto dto);



}
