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
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "factureClient")
public class FactureClient extends AbstractEntity {
    @Column(name = "code")
    private String code;

    @Column(name = "date_facture")
    private Instant dateFacture;

    @ManyToOne
    @JoinColumn(name = "idCommandeClient")
    private CommandeClient commandeClient;
    
    public List<LigneCommandeClient> getLignesCommandeClient() {
        return commandeClient.getLignecommandeclient();
    }
    @Override
	public String toString() {
	    return "factureClient{" +
	            ", name=" + code +
	            ", designation=" + dateFacture +
	            '}';
}


    
}

