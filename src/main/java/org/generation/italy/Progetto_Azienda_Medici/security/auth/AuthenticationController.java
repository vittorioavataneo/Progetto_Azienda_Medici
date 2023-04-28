package org.generation.italy.Progetto_Azienda_Medici.security.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/registration/patient")
  public ResponseEntity<AuthenticationResponse> registerUser(
      @RequestBody RegisterRequestUser request
  ) {
    return ResponseEntity.ok(service.registerUser(request));
  }

  @PostMapping("/registration/doctor")
  public ResponseEntity<AuthenticationResponse> registerDoctor(
          @RequestBody RegisterRequestDoctor request
  ) {
    return ResponseEntity.ok(service.registerDoctor(request));
  }

  @PostMapping("/registration/admin")
  public ResponseEntity<AuthenticationResponse> registerAdmin(
          @RequestBody RegisterRequestAdmin request
  ) {
    return ResponseEntity.ok(service.registerAdmin(request));
  }

  @PostMapping("/authentication/patient")
  public ResponseEntity<AuthenticationResponse> authenticatePatient(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticatePatient(request));
  }

  @PostMapping("/authentication/doctor")
  public ResponseEntity<AuthenticationResponse> authenticateDoctor(
          @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticateDoctor(request));
  }

  @PostMapping("/authentication/admin")
  public ResponseEntity<AuthenticationResponse> authenticateAdmin(
          @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticateAdmin(request));
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }


}
