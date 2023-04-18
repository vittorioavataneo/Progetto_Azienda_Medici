package org.generation.italy.Progetto_Azienda_Medici.model.entities;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.time.LocalDate;

@Entity
@Table(name = "visita_medica")
@Getter
@Setter
@NoArgsConstructor
public class MedicalExamination {
    @Id
    @GeneratedValue(generator = "visita_medica_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "visita_medica_generator", sequenceName = "visita_medica_sequence", allocationSize = 1)
    @Column(name = "id_visita_medica")
    private long id;
    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "id_paziente")
    private Patient patient;
    @Column(name = "data_prenotazione")
    private LocalDate reservationDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_di_contatto")
    @Type(PostgreSQLEnumType.class)
    private Contact contact;
    @ManyToOne
    @JoinColumn(name = "id_visita_specialistica")
    private Specialization specialization;
    @Enumerated(EnumType.STRING)
    @Column(name = "pagamento")
    @Type(PostgreSQLEnumType.class)
    private Payment payment;
    @Enumerated(EnumType.STRING)
    @Column(name = "pacchetto")
    @Type(PostgreSQLEnumType.class)
    private ExaminationPackage examinationPackage;
    private String note;
    @Column(name = "note_pagamento")
    private String paymentNote;
    @Column(name = "fatturazione")
    private boolean billing;
    @Enumerated(EnumType.STRING)
    @Column(name = "stato")
    @Type(PostgreSQLEnumType.class)
    public State state;

    public MedicalExamination(long id, Doctor doctor, Patient patient, LocalDate reservationDate,
                              Contact contact, Specialization specialization, Payment payment,
                              ExaminationPackage examinationPackage, String note, String paymentNote,
                              boolean billing, State state) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
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
