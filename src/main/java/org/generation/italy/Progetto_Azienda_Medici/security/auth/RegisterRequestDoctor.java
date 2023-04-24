package org.generation.italy.Progetto_Azienda_Medici.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.generation.italy.Progetto_Azienda_Medici.dtos.AddressDto;
import org.generation.italy.Progetto_Azienda_Medici.dtos.SpecializationDto;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDoctor {

    private String firstname;
    private String lastname;
    private String dob;
    private String sex;
    private String email;
    private String cellNumber;
    private String password;
    private AddressDto address;
    private String doctorCode;
    private SpecializationDto specialization;

}
