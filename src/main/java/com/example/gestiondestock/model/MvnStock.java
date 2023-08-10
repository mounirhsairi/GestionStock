package com.example.gestiondestock.model;

import java.math.BigDecimal;
import java.time.Instant;

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
@Table(name="Mvnstock")
public class MvnStock extends AbstractEntity {
	
	@Column(name = "datemvt")
	private Instant dateMvt ;
	@Column(name = "quantite")
	private BigDecimal quantite ;
	@ManyToOne
	@JoinColumn(name="idArticle")
	private Article article ;
	@Column(name = "typemvt")
	private TypeMvtStock typeMvt ;
	@Column(name = "sourcemvt")
	private SourceMvtStock sourceMvt ;
	@Column(name = "idEntreprise")
	private Integer idEntreprise ;
	
}
