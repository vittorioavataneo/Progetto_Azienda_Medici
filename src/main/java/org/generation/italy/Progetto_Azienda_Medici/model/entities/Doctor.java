package org.generation.italy.Progetto_Azienda_Medici.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "id_medico")
@Table(name = "medico")
@Getter
@Setter
@NoArgsConstructor
public class Doctor extends Person{

    @ManyToOne
    @JoinColumn(name = "id_indirizzo")
    private Address address;
    @Column( name = "codice_dottore")
    private String doctorCode;
    @ManyToOne
    @JoinColumn(name = "id_visita_specialistica")
    private Specialization specialization;
    @Column(name = "fatturazione")
    private boolean billing;
    @OneToMany(mappedBy = "doctor")
    private Set<MedicalExamination> medicalExaminations;

    public Doctor(long id, String firstname, String lastname, LocalDate dob,
                  String cellNumber, String email, Sex sex, String username,
                  String password, Address address, String doctorCode,
                  Specialization specialization, boolean billing) {
        super(id, firstname, lastname, dob, cellNumber, email, sex, username, password);
        this.address = address;
        this.doctorCode = doctorCode;
        this.specialization = specialization;
        this.billing = billing;
    }

    public Doctor(long id, String firstname, String lastname, LocalDate dob,
                  String cellNumber, String email, Sex sex, String username,
                  String password, Address address, String doctorCode,
                  Specialization specialization, boolean billing, Set<MedicalExamination> medicalExaminations) {
        super(id, firstname, lastname, dob, cellNumber, email, sex, username, password);
        this.address = address;
        this.doctorCode = doctorCode;
        this.specialization = specialization;
        this.billing = billing;
        this.medicalExaminations = medicalExaminations;
    }
}
