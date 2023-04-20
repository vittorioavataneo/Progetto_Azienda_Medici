package org.generation.italy.Progetto_Azienda_Medici.dtos;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Sex;
import org.generation.italy.Progetto_Azienda_Medici.security.user.User;
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
    protected User user;

    public PersonDto(long id, String firstname, String lastname, String dob, String sex, String cellNumber, User user) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.sex = sex;
        this.cellNumber = cellNumber;
        this.user = user;
    }
}
