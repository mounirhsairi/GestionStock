package com.example.gestiondestock.model;

import java.time.Instant;
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
@EqualsAndHashCode(callSuper = true )
@Entity
@Table(name="Ventes")
public class Ventes extends AbstractEntity {
	@Column(name = "idEntreprise")
	private String idEntreprise ;
	@Column (name = "code")
	private String code ;
	@Column (name = "datevente")
	private Instant dateVente ;
	@Column (name = "commentaire")
	private String commentaire ;
	@OneToMany(mappedBy ="vente")
	private List<LigneVente>ligneVente ;
	
	
}
