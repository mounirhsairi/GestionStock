package com.example.gestiondestock.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
@EqualsAndHashCode(callSuper = true )
@Entity
@Table(name="entreprise")
public class Entreprise extends AbstractEntity {
	@Column (name = "nom",unique = true)
	private String  nom ;
	@Column (name = "description")
	private String  description ;
	@Embedded
	private Adresse adresse ;
	@Column (name = "codeFiscal")
	private String  codeFiscal ;
	@Column (name = "photo")
	private String  photo ;
	@Column (name = "email")
	private String  email ;
	@Column (name = "numtel")
	private String  numtel ;
	@Column (name = "siteWeb")
	private String  siteWeb ;
	@OneToMany(mappedBy = "entreprise")
	private List<User> utilisateur ;
	 public String getLowerCaseName() {
	        return this.nom.toLowerCase();
	    }
}
