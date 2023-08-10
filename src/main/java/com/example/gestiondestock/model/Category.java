package com.example.gestiondestock.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Category")
public class Category extends AbstractEntity {
    @Column(name = "idEntreprise")
    private Integer idEntreprise;

    @Column(name = "codeCategory")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "designation")
    private String designation;

    @OneToMany(mappedBy = "category")
    private List<Article> articles;
    @Override
	public String toString() {
	    return "Category{" +
	            ", name=" + name +
	            ", designation=" + designation +
	            '}';
}
}