package com.example.gestiondestock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetails {
    private String articleName;
    private String prixUnitaire;
    private String quantite;
}
