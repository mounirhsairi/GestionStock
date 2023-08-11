package com.example.gestiondestock.model;

import java.math.BigDecimal;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true )
@Entity
@Table(name="article")
public class Article extends AbstractEntity{
@Column(name = "idEntreprise")
private Integer idEntreprise ;
@Column (name = "CodeArticle")
 private String codeArticle ;
@Column (name = "designation")
private String designation;
@Column (name = "prixUnitaireHt")
private BigDecimal prixUnitaireHt ;
@Column (name = "TauxTva")
private BigDecimal TauxTva ;
@Column (name = "prixUnitaireTtc")
private BigDecimal prixUnitaireTtc ;
@Column (name = "photo")
private String photo ;
@Column (name = "quantite")
private BigDecimal quantite ;
@ManyToOne
@JoinColumn(name = "idCategory")
private Category category ;

}
