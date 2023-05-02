package org.generation.italy.Progetto_Azienda_Medici.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SimplePersonDto {

    protected long id;
    protected String firstname;
    protected String lastname;

    public SimplePersonDto(long id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
