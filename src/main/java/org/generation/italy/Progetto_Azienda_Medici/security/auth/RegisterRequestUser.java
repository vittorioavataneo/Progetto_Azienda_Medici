package org.generation.italy.Progetto_Azienda_Medici.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Sex;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestUser {

  private String firstname;
  private String lastname;
  private LocalDate dob;
  private Sex sex;
  private String email;
  private String cellNumber;
  private String password;
  private String taxCode;

}
