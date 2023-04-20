package org.generation.italy.Progetto_Azienda_Medici.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.italy.Progetto_Azienda_Medici.security.user.User;

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
                  String cellNumber, Sex sex, User user, Address address, String doctorCode,
                  Specialization specialization, boolean billing, Set<MedicalExamination> medicalExaminations) {
        super(id, firstname, lastname, dob, cellNumber, sex, user);
        this.address = address;
        this.doctorCode = doctorCode;
        this.specialization = specialization;
        this.billing = billing;
        this.medicalExaminations = medicalExaminations;
    }
}
