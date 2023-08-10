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
@Table(name="Client")
public class Client extends AbstractEntity{
	@Column(name = "idEntreprise")
	private Integer idEntreprise ;
	@Column (name = "nom")
	private String  nom ;
	@Column (name = "prenom")
	private String  prenom ;
	@Embedded
	private Adresse adresse ;
	@Column (name = "photo")
	private String photo ;
	@Column (name = "mail")
	private String  mail ;
	@Column (name = "Numtel")
	private String Numtel  ;
	@OneToMany(mappedBy = "client")
	private List<CommandeClient> CommandeClients ;
	@Override
	public String toString() {
	    return "Client{" +
	            ", quantite=" + nom +
	            ", prixUnitaire=" + prenom +
	            '}';
	}
}
