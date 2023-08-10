package com.example.gestiondestock;


import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication

@EnableJpaAuditing
/*@EntityScan("com.example.gestiondestock.model")

@ComponentScan(basePackages = {"com.example.gestiondestock.controller", "com.example.gestiondestock.Service"})*/
public class GestiondestockApplication {
    public static void main(String[] args) {
        SpringApplication.run(GestiondestockApplication.class, args);
        System.out.println("hello");
    }
    
}

