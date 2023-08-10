package com.example.gestiondestock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestiondestock.model.Category;

public interface CategoryRepository  extends JpaRepository<Category ,Integer> {
	List<Category> findAllByCode(String code);
}
