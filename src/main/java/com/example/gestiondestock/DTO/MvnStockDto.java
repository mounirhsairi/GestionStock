package com.example.gestiondestock.DTO;


import java.math.BigDecimal;
import java.time.Instant;

import com.example.gestiondestock.model.MvnStock;
import com.example.gestiondestock.model.SourceMvtStock;
import com.example.gestiondestock.model.TypeMvtStock;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MvnStockDto {
	private ArticleDto article ;
	private Integer id ;
	private Instant dateMvt ;
	private BigDecimal quantite ;
	private TypeMvtStock typeMvt ;
	private Integer idEntreprise ;
	private SourceMvtStock sourceMvt ;

	public static MvnStockDto fromEntity(MvnStock mvnStock) {
        return MvnStockDto.builder()
                .article(ArticleDto.fromEntity(mvnStock.getArticle()))
                .id(mvnStock.getId())
                .dateMvt(mvnStock.getDateMvt())
                .quantite(mvnStock.getQuantite())
                .typeMvt(mvnStock.getTypeMvt())  // Assign the enum value directly
                .sourceMvt(mvnStock.getSourceMvt())
                .idEntreprise(mvnStock.getIdEntreprise())
                .build();
    }
	public static MvnStock toEntity(MvnStockDto mvnStockDto) {
	    MvnStock mvnStock = new MvnStock();
	    mvnStock.setArticle(ArticleDto.toEntity(mvnStockDto.getArticle()));
	    mvnStock.setId(mvnStockDto.getId());
	    mvnStock.setDateMvt(mvnStockDto.getDateMvt());
	    mvnStock.setQuantite(mvnStockDto.getQuantite());
	    mvnStock.setTypeMvt(mvnStockDto.getTypeMvt());
	    mvnStock.setSourceMvt(mvnStockDto.getSourceMvt());
	    mvnStock.setIdEntreprise(mvnStockDto.getIdEntreprise());
	    return mvnStock;
	}
	


}
