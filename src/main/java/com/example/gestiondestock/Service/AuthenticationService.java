package com.example.gestiondestock.Service;

import java.io.IOException;
import com.example.gestiondestock.repository.EntrepriseRepository;
import com.example.gestiondestock.repository.RolesRepository;

import java.time.Instant;
import java.util.Collections;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.gestiondestock.model.Entreprise;
import com.example.gestiondestock.model.Roles;
import com.example.gestiondestock.model.Token;
import com.example.gestiondestock.repository.TokenRepository;
import com.example.gestiondestock.token.TokenType;
import com.example.gestiondestock.DTO.RolesDto;
import com.example.gestiondestock.DTO.auth.AuthenticationResponse;
import com.example.gestiondestock.DTO.auth.LoginDto;
import com.example.gestiondestock.DTO.auth.SignUpDto;
import com.example.gestiondestock.model.User;
import com.example.gestiondestock.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final UserRepository repository;
	  private final TokenRepository tokenRepository;
	  private final PasswordEncoder passwordEncoder;
	  private final JwtService jwtService;
	  private final AuthenticationManager authenticationManager;
	  private final EntrepriseRepository entrepriseRepository;
	  private final RolesRepository rolesRepository ;

	  public AuthenticationResponse register(SignUpDto request) {
		  
	    String normalizedEntrepriseName = request.getEntrepriseName().toLowerCase();
        Entreprise entreprise = entrepriseRepository.findByNom(normalizedEntrepriseName);
        if (entreprise == null) {
            entreprise = new Entreprise();
            entreprise.setNom(request.getEntrepriseName());
            entreprise.setCodeFiscal(request.getEntrepriseCodeFiscal());
            entreprise.setDescription(request.getEntrepriseDescription());
            entreprise.setEmail(request.getEntrepriseEmail());
            entreprise.setNumtel(request.getEntrepriseTel());
    	    entreprise.setCreationDate(Instant.now());

            entreprise = entrepriseRepository.save(entreprise);
        }
        String normalizedRoleName = request.getRoleName().toLowerCase();
	    Roles role =rolesRepository.findByName(normalizedRoleName);
	    if(role == null)
	    {
	    	role = new Roles();
	    	role.setName(request.getRoleName());
	    	role.setCreationDate(Instant.now());
	    	role.setIdEntreprise(entreprise.getId());
	    	role=rolesRepository.save(role);
	    }
        
	    var user = User.builder()
	        .name(request.getName())
	        .username(request.getUsername())
	        .email(request.getEmail())
	        .password(passwordEncoder.encode(request.getPassword()))
	        .build();
	    user.setCreationDate(Instant.now());
	    user.setIdEntreprise(entreprise.getId());
	    var savedUser = repository.save(user);
	    role.setIdUser(savedUser.getId());
	    role = rolesRepository.save(role);

	    var jwtToken = jwtService.generateToken(user);
	    var refreshToken = jwtService.generateRefreshToken(user);
	    saveUserToken(savedUser, jwtToken);
	    return AuthenticationResponse.builder()
	        .accessToken(jwtToken)
	            .refreshToken(refreshToken)
	        .build();
	  }
	  

	  public AuthenticationResponse authenticate(LoginDto request) {
	    authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(
	            request.getEmail(),
	            request.getPassword()
	        )
	    );
	    var user = repository.findByEmail(request.getEmail())
	        .orElseThrow();
	    var jwtToken = jwtService.generateToken(user);
	    var refreshToken = jwtService.generateRefreshToken(user);
	    revokeAllUserTokens(user);
	    saveUserToken(user, jwtToken);
	    return AuthenticationResponse.builder()
	        .accessToken(jwtToken)
	            .refreshToken(refreshToken)
	        .build();
	  }

	  private void saveUserToken(User user, String jwtToken) {
	    var token = Token.builder()
	        .user(user)
	        .token(jwtToken)
	        .tokenType(TokenType.BEARER)
	        .expired(false)
	        .revoked(false)
	        .build();
	    tokenRepository.save(token);
	  }

	  private void revokeAllUserTokens(User user) {
	    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
	    if (validUserTokens.isEmpty())
	      return;
	    validUserTokens.forEach(token -> {
	      token.setExpired(true);
	      token.setRevoked(true);
	    });
	    tokenRepository.saveAll(validUserTokens);
	  }

	  public void refreshToken(
	          HttpServletRequest request,
	          HttpServletResponse response
	  ) throws IOException {
	    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
	    final String refreshToken;
	    final String userEmail;
	    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
	      return;
	    }
	    refreshToken = authHeader.substring(7);
	    userEmail = jwtService.extractUsername(refreshToken);
	    if (userEmail != null) {
	      var user = this.repository.findByEmail(userEmail)
	              .orElseThrow();
	      if (jwtService.isTokenValid(refreshToken, user)) {
	        var accessToken = jwtService.generateToken(user);
	        revokeAllUserTokens(user);
	        saveUserToken(user, accessToken);
	        var authResponse = AuthenticationResponse.builder()
	                .accessToken(accessToken)
	                .refreshToken(refreshToken)
	                .build();
	        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
	      }
	    }
	  }
	}