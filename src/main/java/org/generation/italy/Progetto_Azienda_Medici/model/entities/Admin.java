package org.generation.italy.Progetto_Azienda_Medici.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.italy.Progetto_Azienda_Medici.security.user.User;

import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "id_admin")
@Table(name = "admin")
@Getter
@Setter
@NoArgsConstructor
public class Admin extends Person{

    @Column( name = "codice_admin")
    private String adminCode;

    public Admin(long id, String firstname, String lastname, LocalDate dob,
                 String cellNumber, Sex sex, User user, String adminCode) {
        super(id, firstname, lastname, dob, cellNumber, sex, user);
        this.adminCode = adminCode;
    }
}
