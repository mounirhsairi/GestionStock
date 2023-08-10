package com.example.gestiondestock.Service.Impl;

import java.util.List;

import java.util.ArrayList;



import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.gestiondestock.DTO.ArticleDto;
import com.example.gestiondestock.DTO.CategoryDto;
import com.example.gestiondestock.DTO.CommandeClientDto;
import com.example.gestiondestock.Service.CategoryService;
import com.example.gestiondestock.exception.EntityNotFoundException;
import com.example.gestiondestock.exception.ErrorCodes;
import com.example.gestiondestock.exception.InvalidEntityException;
import com.example.gestiondestock.exception.InvalidOperationException;
import com.example.gestiondestock.model.Article;
import com.example.gestiondestock.model.Category;
import com.example.gestiondestock.model.Client;
import com.example.gestiondestock.repository.ArticleRepository;
import com.example.gestiondestock.repository.CategoryRepository;
import com.example.gestiondestock.validator.CategoryValidator;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository ;
	private  ArticleRepository articleRepository ;
	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository, ArticleRepository articleRepository) {
	    this.categoryRepository = categoryRepository;
	    this.articleRepository=articleRepository;
	}

	@Override
	public CategoryDto save(CategoryDto dto) {
		List<String> errors = CategoryValidator.validate(dto);
		if(!errors.isEmpty()) {
			log.error("Category is not valid",dto);
			throw new InvalidEntityException("la categorie n'est pas valide ",ErrorCodes.CATEGORY_NOT_VALID,errors);
		}
	return CategoryDto.fromEntity(categoryRepository.save(CategoryDto.toEntity(dto)));
}
	
	@Override
	public List<CategoryDto> findAllByCodeCategory(String codeCategory) {
		if(!StringUtils.hasLength(codeCategory))
		{
			log.error("category Code is null");
		return null;
		}
		List<Category> category = categoryRepository.findAllByCode(codeCategory);
		if (category.isEmpty()) {
		    throw new EntityNotFoundException("Aucune categorie avec le code " + codeCategory, ErrorCodes.CATEGORY_NOT_FOUND);
		}


	    return category.stream()
	            .map(CategoryDto::fromEntity)
	            .collect(Collectors.toList());
	}
	


	

	@Override
	public CategoryDto findById(Integer id) {
		if(id == null) {
		log.error("Category Id is null");
			return null;
	}
		Optional<Category> category =categoryRepository.findById(id) ;
		return Optional.of(CategoryDto.fromEntity(category.get())).orElseThrow(()-> new EntityNotFoundException("aucune categorie avec le code "+ id,ErrorCodes.CATEGORY_NOT_FOUND));
	}
	
	
	
	@Override
	public Page<CategoryDto> findAll(Pageable pageable) {

		return categoryRepository.findAll(pageable).map(CategoryDto::fromEntity);//.stream().map(CategoryDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {

		if(id == null) {
			log.error("Category Id is null");
				return ;
		}
		List<Article>articles =	articleRepository.findAllByCategoryId(id);
		if(!articles.isEmpty())
		{
			throw new InvalidOperationException("impossible de supprimer une categorie qui est deja utiliser ",ErrorCodes.CATEGORY_ALLREADY_IN_USE);

		}
			categoryRepository.deleteById(id);
		
	}

	@Override
	public CategoryDto updateCategory(Integer idCategory, CategoryDto categoryDto) {

		if(idCategory == null)
		{
			log.error("category ID is Null");
            throw new InvalidOperationException("impossible de modifier la categorie avec un id null", ErrorCodes.CATEGORY_NOT_VALID);
 
		}
    	Optional<Category> categoryOptional =categoryRepository.findById(idCategory);
    	if(categoryOptional.isEmpty())
    	{
    		throw new InvalidEntityException("Aucune category avec l'ID " +idCategory
                    + " n'a été trouvé dans la base de données", ErrorCodes.CATEGORY_NOT_FOUND);
        
    	}
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUser = authentication.getName();
        categoryDto.setLastModifiedBy(loggedInUser);
    	
    	return CategoryDto.fromEntity(categoryRepository.save(CategoryDto.toEntity(categoryDto)));
	}
	
	
	}


