package com.example.gestiondestock.controller.api;


import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gestiondestock.DTO.ArticleDto;
import com.example.gestiondestock.DTO.CategoryDto;
import com.example.gestiondestock.DTO.LigneCommandeClientDto;
import com.example.gestiondestock.DTO.LigneCommandeFournisseurDto;
import com.example.gestiondestock.DTO.LigneVenteDto;
import com.example.gestiondestock.model.Article;
import com.example.gestiondestock.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
@Api(Constants.APP_ROOT+"/articles")
public interface ArticleApi {
	
	@PostMapping(value = Constants.APP_ROOT + "/articles/Create", consumes = "application/json", produces = "application/json")
	@ApiOperation(value="enregistrer un article",notes="cette methode permet d'enregistrer ou modifier un article", response=ArticleDto.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "L'objet article créé/modifié"),
	        @ApiResponse(code = 400, message = "L'objet article n'estpas valide")

	})
	ArticleDto save(@RequestBody ArticleDto dto);
	@GetMapping(value = Constants.APP_ROOT + "/articles/{idArticle}",produces = "application/json")
	@ApiOperation(value="rechercher un article par son id",notes="cette methode permet de rechercher un article", response=ArticleDto.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "L'article a ete trouve dans la BDD"),
	        @ApiResponse(code = 404, message = "L'article n'existe pas")

	})
	ArticleDto findById(@PathVariable("idArticle") Integer id);
	
	@GetMapping(value = Constants.APP_ROOT + "/articles/designation/{designation}",produces = "application/json")
	@ApiOperation(value="rechercher un article par son id",notes="cette methode permet de rechercher un article", response=ArticleDto.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "L'article a ete trouve dans la BDD"),
	        @ApiResponse(code = 404, message = "Pas d'articles")
	})
	List<ArticleDto> findAllByDesignationArticle(@PathVariable("codeArticle") String designation);
	@GetMapping(value = Constants.APP_ROOT + "/articles/code/{codeArticle}",produces = "application/json")
	@ApiOperation(value="rechercher un article par son id",notes="cette methode permet de rechercher un article", responseContainer="list<ArticleDto>")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "L'article a ete trouve dans la BDD"),
	        @ApiResponse(code = 404, message = "Pas d'articles")
	})
	

	List<ArticleDto> findAllByCodeArticle(@PathVariable("codeArticle")String code);
	@PatchMapping(value = Constants.APP_ROOT + "/articles/update/{idArticle}", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "mettre à jour un article par son code", notes = "cette méthode permet de mettre à jour un article", response = ArticleDto.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "L'article a été mis à jour avec succès"),
	    @ApiResponse(code = 404, message = "Aucun article n'a été trouvé avec le code fourni")
	})
	ArticleDto updateArticle(@PathVariable("idArticle") Integer idArticle, @RequestBody ArticleDto articleDto);
	@GetMapping(value = Constants.APP_ROOT + "/articles/all",produces = "application/json")
	@ApiOperation(value="renvoie la liste des article ",notes="cette methode permet de renvoyer la liste des articles ", responseContainer="list<ArticleDto>")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Le mise a jour d'article se fait"),

	})
	List<ArticleDto> findAll();
	
	@DeleteMapping(value = Constants.APP_ROOT + "/articles/delete/{idArticle}")
	@ApiOperation(value="supprimer un article ",notes="cette methode permet de supprimer un article ", response=ArticleDto.class)
	void delete(@PathVariable("idArticle")Integer id);
	
	@GetMapping(value = Constants.APP_ROOT + "/historique/vente/{idArticle}",produces = "application/json")

	List<LigneVenteDto> findHistoriqueVentes(@PathVariable("idArticle")Integer idArticle) ;
	@GetMapping(value = Constants.APP_ROOT + "/articles/historique/commandeClient/{idArticle}",produces = "application/json")

	List<LigneCommandeClientDto> findHistoriqueCommandesClient(@PathVariable("idArticle")Integer idArticle) ;
	@GetMapping(value = Constants.APP_ROOT + "/articles/historique/commandeFournisseur/{idArticle}",produces = "application/json")

	List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(@PathVariable("idArticle")Integer idArticle) ;
	@GetMapping(value = Constants.APP_ROOT + "/articles/filter/{idCategory}",produces = "application/json")

	List<ArticleDto> findAllArticleByIdCategory(@PathVariable("idCategory")Integer idCategory) ;

	
	
    
}
