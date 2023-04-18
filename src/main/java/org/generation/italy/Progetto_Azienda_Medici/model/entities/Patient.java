package org.generation.italy.Progetto_Azienda_Medici.model.entities;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "id_paziente")
@Table(name = "paziente")
@Getter
@Setter
@NoArgsConstructor
public class Patient extends Person{

    @ManyToOne //forse @ManyToMany
    @JoinColumn(name = "id_medico")
    private Doctor doctor;
    @Column(name = "data_prenotazione")
    private LocalDate reservationDate;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "tipo_di_contatto")
    @Type(PostgreSQLEnumType.class)
    private Contact contact;
    @ManyToOne
    @JoinColumn(name = "id_visita_specialistica")
    private Specialization specialization;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "pagamento")
    @Type(PostgreSQLEnumType.class)
    private Payment payment;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "pacchetto")
    @Type(PostgreSQLEnumType.class)
    private ExaminationPackage examinationPackage;
    private String note;
    @Column(name = "note_pagamento")
    private String paymentNote;
    @Column(name = "fatturazione")
    private boolean billing;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "stato")
    @Type(PostgreSQLEnumType.class)
    public State state;

    public Patient(long id, String firstname, String lastname, LocalDate dob, Sex sex, String cellNumber, String email, Doctor doctor, LocalDate reservationDate,
                   Contact contact, Specialization specialization, Payment payment,
                   ExaminationPackage examinationPackage, String note, String paymentNote, boolean billing, State state) {
        super(id, firstname, lastname, dob, sex, cellNumber, email);
        this.doctor = doctor;
        this.reservationDate = reservationDate;
        this.contact = contact;
        this.specialization = specialization;
        this.payment = payment;
        this.examinationPackage = examinationPackage;
        this.note = note;
        this.paymentNote = paymentNote;
        this.billing = billing;
        this.state = state;
    }
}
