package com.example.gestiondestock.controller.api;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gestiondestock.DTO.CategoryDto;
import com.example.gestiondestock.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(Constants.APP_ROOT+"/categories")
public interface CategoryApi {
	
	@PostMapping(value = Constants.APP_ROOT + "/categories/Create", consumes = "application/json", produces = "application/json")
	@ApiOperation(value="enregistrer une Category",notes="cette methode permet d'enregistrer ou modifier une category", response=CategoryDto.class)
	CategoryDto save(@RequestBody CategoryDto dto);
	@GetMapping(value = Constants.APP_ROOT + "/categories/{idCategory}",produces = "application/json")
	@ApiOperation(value="rechercher une categorie  par son id",notes="cette methode permet de rechercher une category", response=CategoryDto.class)
	CategoryDto findById(@PathVariable("idCategory") Integer id);
	
	@GetMapping(value = Constants.APP_ROOT + "/categories/code/{codeCategory}",produces = "application/json")
	@ApiOperation(value="renvoie la liste des categories par code ",notes="cette methode permet de renvoyer la liste des categories par code ", responseContainer="list<CategoryDto>")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "La liste des categories / une liste vide"),

	})
	List<CategoryDto> findAllByCodeCategory(@PathVariable("codeCategory")String codeCategory);
	@PutMapping(value = Constants.APP_ROOT + "/categories/update/{idCategory}", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "mettre à jour une categorie par son code", notes = "cette méthode permet de mettre à jour un article", response = CategoryDto.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "La categorie a été mis à jour avec succès"),
	    @ApiResponse(code = 404, message = "Aucune categorie n'a été trouvé avec le code fourni")
	})
	CategoryDto updateCategory(@PathVariable("idCategory")Integer id,@RequestBody CategoryDto categoryDto);
	@GetMapping(value = Constants.APP_ROOT + "/categories/all",produces = "application/json")
	@ApiOperation(value="renvoie la liste des categories ",notes="cette methode permet de renvoyer la liste des categories ", responseContainer="list<CategoryDto>")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "La liste des categories / une liste vide"),

	})
	Page<CategoryDto> findAll(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1") int size);
	@DeleteMapping(value = Constants.APP_ROOT + "/categories/delete/{idCategory}")
	@ApiOperation(value="supprimer une category ",notes="cette methode permet de supprimer une category ", response=CategoryDto.class)

	void delete(@PathVariable("idCategory")Integer id);

}
