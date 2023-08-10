package com.example.gestiondestock.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="roles")
public class Roles extends AbstractEntity {
	@Column(name = "idEntreprise")
	private Integer idEntreprise ;
	@Column (name = "Name")
	private String name;
	@ManyToOne
	@JoinColumn(name="idUtilisateur")
	private User Utilisateur ;
	 public String getLowerCaseName() {
	        return this.name.toLowerCase();
	    }
	 public Integer getIdUser() {
	        if (Utilisateur != null) {
	            return Utilisateur.getId();
	        }
	        return null;
	    }

	    // Setter method for id_entreprise
	    public void setIdUser(Integer idEntreprise) {
	        if (Utilisateur == null) {
	        	Utilisateur = new User();
	        }
	        Utilisateur.setId(idEntreprise);
	    }
}

