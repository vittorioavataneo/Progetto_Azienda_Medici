package org.generation.italy.Progetto_Azienda_Medici.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @ManyToOne
    @JoinColumn(name = "id_indirizzo")
    private Address address;

    public Admin(long id, String firstname, String lastname, LocalDate dob,
                 String cellNumber, String email, Sex sex, String username,
                 String password, String adminCode, Address address) {
        super(id, firstname, lastname, dob, cellNumber, email, sex, username, password);
        this.adminCode = adminCode;
        this.address = address;
    }
}
