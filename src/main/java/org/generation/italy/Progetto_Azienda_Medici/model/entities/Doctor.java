package org.generation.italy.Progetto_Azienda_Medici.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "id_medico")
@Table(name = "medico")
@Getter
@Setter
@NoArgsConstructor
public class Doctor extends Person{

    public String username;
    public String password;
    @ManyToOne
    @JoinColumn(name = "id_indirizzo")
    private Address address;
    @ManyToOne
    @JoinColumn(name = "id_visita_specialistica")
    private Specialization specialization;
    @Column(name = "fatturazione")
    private boolean billing;

    public Doctor(long id, String firstname, String lastname, LocalDate dob, Sex sex, String cellNumber,
                  String email, String username, String password,
                  Address address, Specialization specialization, boolean billing) {
        super(id, firstname, lastname, dob, sex, cellNumber, email);
        this.username = username;
        this.password = password;
        this.address = address;
        this.specialization = specialization;
        this.billing = billing;
    }
}
