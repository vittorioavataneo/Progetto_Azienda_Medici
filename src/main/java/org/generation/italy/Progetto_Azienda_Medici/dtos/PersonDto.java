package org.generation.italy.Progetto_Azienda_Medici.dtos;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Sex;
import org.hibernate.annotations.Type;

@Getter
@Setter
@NoArgsConstructor
public abstract class PersonDto {

    protected long id;
    protected String firstname;
    protected String lastname;
    protected String dob;
    protected String sex;
    protected String cellNumber;
    protected String email;
    private String username;
    private String password;

    public PersonDto(long id, String firstname, String lastname, String dob, String sex,
                     String cellNumber, String email, String username, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.sex = sex;
        this.cellNumber = cellNumber;
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
