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
    private String street;
    private String cap;
    private String city;
    private String province;
    private String country;
    private String doctorCode;
    private String specializationName;

}
