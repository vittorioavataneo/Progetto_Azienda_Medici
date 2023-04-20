package org.generation.italy.Progetto_Azienda_Medici.security.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/registerUser")
  public ResponseEntity<AuthenticationResponse> registerUser(
      @RequestBody RegisterRequestUser request
  ) {
    return ResponseEntity.ok(service.registerUser(request));
  }

  @PostMapping("/registerDoctor")
  public ResponseEntity<AuthenticationResponse> registerDoctor(
          @RequestBody RegisterRequestDoctor request
  ) {
    return ResponseEntity.ok(service.registerDoctor(request));
  }

  @PostMapping("/registerAdmin")
  public ResponseEntity<AuthenticationResponse> registerAdmin(
          @RequestBody RegisterRequestAdmin request
  ) {
    return ResponseEntity.ok(service.registerAdmin(request));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }


}
