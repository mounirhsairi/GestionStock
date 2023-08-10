package com.example.gestiondestock.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "facture_fournisseur")
public class FactureFournisseur extends AbstractEntity {
    @Column(name = "code")
    private String code;

    @Column(name = "date_facture")
    private Instant dateFacture;

    @ManyToOne
    @JoinColumn(name = "idCommandeFournisseur")
    private CommandeFournisseur commandeFournisseur;

    /*@OneToMany(mappedBy = "factureFournisseur")
    private List<LigneFactureFournisseur> ligneFactureFournisseurs;*/

    
    
}
