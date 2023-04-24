package org.generation.italy.Progetto_Azienda_Medici.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SimplePersonDto {

    protected String firstname;
    protected String lastname;

    public SimplePersonDto(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
