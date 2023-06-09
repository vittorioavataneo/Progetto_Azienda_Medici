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
public class RegisterRequestAdmin {

    private String firstname;
    private String lastname;
    private String dob;
    private String sex;
    private String email;
    private String cellNumber;
    private String password;
    private String adminCode;
}
