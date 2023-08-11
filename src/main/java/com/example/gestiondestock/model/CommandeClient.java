package com.example.gestiondestock.model;

import java.time.Instant;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="commandeClient")
public class CommandeClient extends AbstractEntity {
	@Column(name = "idEntreprise")
	private Integer idEntreprise ;
	@Column (name = "codeCommandeClient")
	private String codeCommandeClient ;
	@Column (name = "dateCommande")
	private Instant dateCommande ;
	@Column(name="etatCommande")
	private EtatCommande etatCommande ;
	@ManyToOne
	@JoinColumn(name = "idClient")
	private Client client ;
	@OneToMany(mappedBy = "commandeClient")
	private List<LigneCommandeClient> lignecommandeclient ;
	@OneToMany(mappedBy = "commandeClient")
    private List<FactureClient> factureClient;
	@OneToMany(mappedBy = "commandeClient")
    private List<LigneVente> ligneVente;

	
}
