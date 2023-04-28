package org.generation.italy.Progetto_Azienda_Medici.security.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.generation.italy.Progetto_Azienda_Medici.model.data.abstractions.*;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.*;
import org.generation.italy.Progetto_Azienda_Medici.security.config.JwtService;
import org.generation.italy.Progetto_Azienda_Medici.security.token.Token;
import org.generation.italy.Progetto_Azienda_Medici.security.token.TokenRepository;
import org.generation.italy.Progetto_Azienda_Medici.security.token.TokenType;
import org.generation.italy.Progetto_Azienda_Medici.security.user.Role;
import org.generation.italy.Progetto_Azienda_Medici.security.user.User;
import org.generation.italy.Progetto_Azienda_Medici.security.user.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;

import static org.generation.italy.Progetto_Azienda_Medici.utilities.StringUtilities.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final PatientRepository patientRepository;
  private final DoctorRepository doctorRepository;
  private final AdminRepository adminRepository;
  private final AddressRepository addressRepository;
  private final SpecializationRepository specializationRepository;


  public AuthenticationResponse registerUser(RegisterRequestUser request) {
    var user = User.builder()
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .build();
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    saveUserToken(savedUser, jwtToken);
    patientRepository.save(
            new Patient(
                    0,
                    request.getFirstname(),
                    request.getLastname(),
                    fromJSONString(request.getDob()),
                    request.getCellNumber(),
                    fromStringToEnum(Sex.class, request.getSex()),
                    user,
                    request.getTaxCode(),
                    new HashSet<>()
            )
    );
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
            .refreshToken(refreshToken)
        .build();
  }


  public AuthenticationResponse registerDoctor(RegisterRequestDoctor request) {
    var user = User.builder()
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.PROFESSIONAL)
            .build();
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    saveUserToken(savedUser, jwtToken);
    Specialization specialization = specializationRepository.save(
            new Specialization(
                    0,
                    request.getSpecializationName()
            )
    );

    Address address = addressRepository.save(
            new Address(
                    0,
                    request.getStreet(),
                    request.getCap(),
                    request.getCity(),
                    request.getProvince(),
                    request.getCountry()
            )
    );

    doctorRepository.save(
            new Doctor(
                    0,
                    request.getFirstname(),
                    request.getLastname(),
                    fromJSONString(request.getDob()),
                    request.getCellNumber(),
                    fromStringToEnum(Sex.class, request.getSex()),
                    user,
                    address,
                    request.getDoctorCode(),
                    specialization,
                    new HashSet<>()
            )
    );
    return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .build();
  }


  public AuthenticationResponse registerAdmin(RegisterRequestAdmin request) {
    var user = User.builder()
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.ADMIN)
            .build();

    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    saveUserToken(savedUser, jwtToken);

    adminRepository.save(
            new Admin(
                    0,
                    request.getFirstname(),
                    request.getLastname(),
                    fromJSONString(request.getDob()),
                    request.getCellNumber(),
                    fromStringToEnum(Sex.class, request.getSex()),
                    user,
                    request.getAdminCode()
            )
    );
    return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .build();
  }


  public AuthenticationResponse authenticatePatient(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    Role role = user.getRole();
    if (role == Role.USER) {
      var jwtToken = jwtService.generateToken(user);
      var refreshToken = jwtService.generateRefreshToken(user);
      revokeAllUserTokens(user);
      saveUserToken(user, jwtToken);
      return AuthenticationResponse.builder()
              .accessToken(jwtToken)
              .refreshToken(refreshToken)
              .build();
    } else {
      throw new AccessDeniedException("Accesso negato: non sei un paziente");
    }
  }


  public AuthenticationResponse authenticateDoctor(AuthenticationRequest request) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            )
    );
    var user = repository.findByEmail(request.getEmail())
            .orElseThrow();
    Role role = user.getRole();
    if (role == Role.PROFESSIONAL) {
      var jwtToken = jwtService.generateToken(user);
      var refreshToken = jwtService.generateRefreshToken(user);
      revokeAllUserTokens(user);
      saveUserToken(user, jwtToken);
      return AuthenticationResponse.builder()
              .accessToken(jwtToken)
              .refreshToken(refreshToken)
              .build();
    } else {
      throw new AccessDeniedException("Accesso negato: non sei un dottore");
    }
  }


  public AuthenticationResponse authenticateAdmin(AuthenticationRequest request) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            )
    );
    var user = repository.findByEmail(request.getEmail())
            .orElseThrow();
    Role role = user.getRole();
    if (role == Role.ADMIN) {
      var jwtToken = jwtService.generateToken(user);
      var refreshToken = jwtService.generateRefreshToken(user);
      revokeAllUserTokens(user);
      saveUserToken(user, jwtToken);
      return AuthenticationResponse.builder()
              .accessToken(jwtToken)
              .refreshToken(refreshToken)
              .build();
    } else {
      throw new AccessDeniedException("Accesso negato: non sei un admin");
    }
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
