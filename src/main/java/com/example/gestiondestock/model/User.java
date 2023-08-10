package com.example.gestiondestock.model;

import java.time.Instant;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.gestiondestock.model.Token ;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true )
@Entity
@Table(name="Utilisateur" )
public class User extends AbstractEntity implements UserDetails{
	@Column (name = "name")
	private String name ;
	@Column (name = "username")
	private String username ;
	@Column (name = "email")
	private String email ;
	@Column (name = "datedenaissance")
	private Instant datedenaissance ;
	@Column (name = "mdp")
	private String password ;
	@Embedded
	private Adresse adresse ;
	@Column (name = "photo")
	private String photo ;
	@ManyToOne
	@JoinColumn(name="idEntreprise")
	private Entreprise entreprise  ;
	@OneToMany(mappedBy = "Utilisateur")
	private List<Roles> roles ;
	@OneToMany(mappedBy = "user")
	  private List<Token> tokens;
	public Integer getIdEntreprise() {
        if (entreprise != null) {
            return entreprise.getId();
        }
        return null;
    }

    // Setter method for id_entreprise
    public void setIdEntreprise(Integer idEntreprise) {
        if (entreprise == null) {
            entreprise = new Entreprise();
        }
        entreprise.setId(idEntreprise);
    }
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority(roles.getClass().getName()));
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
}
