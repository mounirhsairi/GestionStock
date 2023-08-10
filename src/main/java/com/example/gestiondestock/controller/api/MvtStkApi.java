package com.example.gestiondestock.controller.api;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.gestiondestock.DTO.MvnStockDto;
import com.example.gestiondestock.utils.Constants;

import io.swagger.annotations.Api;

@Api(Constants.APP_ROOT+"/mvtstk")
public interface MvtStkApi {

	@GetMapping(value = Constants.APP_ROOT+"/mvtstk/stockreel/{idArticle}",produces = "application/json")
	BigDecimal stockReelArticle(@PathVariable("idArticle")Integer idArticle);
	@GetMapping(value = Constants.APP_ROOT+"/mvtstk/filter/article/{idArticle}",produces = "application/json")
	List<MvnStockDto> mvtStkArticle(@PathVariable("idArticle")Integer idArticle);
	@PostMapping(value = Constants.APP_ROOT + "/mvtstk/entree", consumes = "application/json", produces = "application/json")
	MvnStockDto entrerStock(@RequestBody MvnStockDto dto);
	@PostMapping(value = Constants.APP_ROOT + "/mvtstk/sortie", consumes = "application/json", produces = "application/json")
	MvnStockDto sortieStock(@RequestBody MvnStockDto dto);
	@PostMapping(value = Constants.APP_ROOT + "/mvtstk/correctionpos", consumes = "application/json", produces = "application/json")
	MvnStockDto correctionStockPos(@RequestBody MvnStockDto dto);
	@PostMapping(value = Constants.APP_ROOT + "/mvtstk/correctionneg", consumes = "application/json", produces = "application/json")
	MvnStockDto correctionStockNeg(@RequestBody MvnStockDto dto);

}
