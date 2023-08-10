package com.example.gestiondestock.model;

 
import java.io.Serializable;



import java.time.Instant;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass

@EntityListeners(AuditingEntityListener.class)
public  class  AbstractEntity implements Serializable  {
 

@Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer id ;
 @CreatedDate
 @Column(name = "creationDate" , nullable = false ,updatable= false)
  private Instant creationDate= Instant.now(); 
 @LastModifiedDate
 @Column(name = "lastModifiedDate")
 private Instant lastUpdateDate ;
 @LastModifiedBy
 @Column(name = "last_modified_by")
 private String lastModifiedBy ;

 

}
