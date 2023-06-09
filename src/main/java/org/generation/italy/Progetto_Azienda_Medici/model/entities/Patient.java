package org.generation.italy.Progetto_Azienda_Medici.model.entities;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.italy.Progetto_Azienda_Medici.security.user.User;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "id_paziente")
@Table(name = "paziente")
@Getter
@Setter
@NoArgsConstructor
public class Patient extends Person{

    @Column(name = "codice_fiscale")
    private String taxCode;

    @OneToMany(mappedBy = "patient")
    private Set<MedicalExamination> medicalExaminations;

    public Patient(long id, String firstname, String lastname, LocalDate dob, String cellNumber,
                   Sex sex, User user, String taxCode, Set<MedicalExamination> medicalExaminations) {
        super(id, firstname, lastname, dob, cellNumber, sex, user);
        this.taxCode = taxCode;
        this.medicalExaminations = medicalExaminations;
    }
}
