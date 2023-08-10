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
@Table(name = "factureRetour")
public class FactureRetour extends AbstractEntity {
    @Column(name = "code")
    private String code;

    @Column(name = "date_facture_retour")
    private Instant dateFactureRetour;

    @ManyToOne
    @JoinColumn(name = "idCommandeClient")
    private CommandeClient commandeClient;

    @OneToMany(mappedBy = "factureRetour")
    private List<LigneFactureRetour> ligneFactureRetour;

    @Override
    public String toString() {
        return "FactureRetour{" +
               "code='" + code + '\'' +
               ", dateFactureRetour=" + dateFactureRetour +
               '}';
    }
}
