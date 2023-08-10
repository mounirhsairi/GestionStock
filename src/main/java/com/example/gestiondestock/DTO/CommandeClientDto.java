package com.example.gestiondestock.DTO;

import java.time.Instant;



import java.util.ArrayList;


import java.util.List;

import com.example.gestiondestock.model.CommandeClient;
import com.example.gestiondestock.model.EtatCommande;
import com.example.gestiondestock.model.LigneCommandeClient;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommandeClientDto {
	private Integer id ;
	private String code ;
	
	private Instant dateCommande ;
	
	private ClientDto client ;
	private EtatCommande etatCommande ;
	private Integer idEntreprise ;
	
	private List<LigneCommandeClientDto> lignecommandeclient ;
	
	
	public static CommandeClientDto fromEntity(CommandeClient commandeClient) {
        if (commandeClient == null) {
            return null;
        }
        List<LigneCommandeClientDto> ligneCommandeClientDtos = new ArrayList<>();

        /*if (commandeClient.getLignecommandeclient() != null) {
            for (LigneCommandeClient ligneCommandeClient : commandeClient.getLignecommandeclient()) {
                ligneCommandeClientDtos.add(LigneCommandeClientDto.fromEntity(ligneCommandeClient));
            }
        }*/
        
        return CommandeClientDto.builder()
        		
                .id(commandeClient.getId())
                .code(commandeClient.getCodeCommandeClient())
                .dateCommande(commandeClient.getDateCommande())
                .etatCommande(commandeClient.getEtatCommande())
                .client(ClientDto.fromEntity(commandeClient.getClient()))
                .idEntreprise(commandeClient.getIdEntreprise())
                //.lignecommandeclient(ligneCommandeClientDtos)
                .build();
    }

    public static CommandeClient toEntity(CommandeClientDto commandeClientDto) {
    	if (commandeClientDto == null) {
            return null;
        }
    	CommandeClient commandeClient = new CommandeClient();
    	commandeClient.setId(commandeClientDto.getId());
    	commandeClient.setCodeCommandeClient(commandeClientDto.getCode());
    	commandeClient.setDateCommande(commandeClientDto.getDateCommande());
    	commandeClient.setEtatCommande(commandeClientDto.getEtatCommande());
    	commandeClient.setIdEntreprise(commandeClientDto.getIdEntreprise());
    	commandeClient.setClient(ClientDto.toEntity(commandeClientDto.getClient()));
    	 /* List<LigneCommandeClient> ligneCommandeClients = new ArrayList<>();
        if (commandeClientDto.getLignecommandeclient() != null) {
            for (LigneCommandeClientDto ligneCommandeClientDto : commandeClientDto.getLignecommandeclient()) {
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligneCommandeClientDto);
                ligneCommandeClient.setCommandeClient(commandeClient);
                ligneCommandeClients.add(ligneCommandeClient);
            }
        }
        commandeClient.setLignecommandeclient(ligneCommandeClients);*/
        return commandeClient ;
    }
    public boolean isCommandeLivree() {
    	return EtatCommande.LIVREE.equals(this.etatCommande);
    }
	
}
