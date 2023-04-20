package org.generation.italy.Progetto_Azienda_Medici.model.entities;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
    @Column(name = "id_visita_medica", columnDefinition = "BIGINT")
    private long id;

    @Column(name = "fatturazione", columnDefinition = "BOOLEAN")
    private boolean billing;

    @ManyToOne
    @JoinColumn(name = "id_medico", columnDefinition = "BIGINT")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "id_paziente", columnDefinition = "BIGINT")
    private Patient patient;

    @Column(name = "data_prenotazione", columnDefinition = "DATE")
    private LocalDate reservationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_di_contatto", columnDefinition = "tipo_di_contatto")
    @Type(PostgreSQLEnumType.class)
    private Contact contact;

    @ManyToOne
    @JoinColumn(name = "id_visita_specialistica", columnDefinition = "BIGINT")
    private Specialization specialization;

    @Enumerated(EnumType.STRING)
    @Column(name = "pagamento", columnDefinition = "pagamento")
    @Type(PostgreSQLEnumType.class)
    private Payment payment;

    @Column(name = "note_pagamento", columnDefinition = "VARCHAR")
    private String paymentNote;

    @Enumerated(EnumType.STRING)
    @Column(name = "pacchetto", columnDefinition = "pacchetto")
    @Type(PostgreSQLEnumType.class)
    private ExaminationPackage examinationPackage;

    @Column(name = "note", columnDefinition = "VARCHAR")
    private String note;

    @Enumerated(EnumType.STRING)
    @Column(name = "stato", columnDefinition = "stato")
    @Type(PostgreSQLEnumType.class)
    public State state;

    public MedicalExamination(long id, boolean billing, Doctor doctor, Patient patient, LocalDate reservationDate,
                              Contact contact, Specialization specialization, Payment payment, String paymentNote,
                              ExaminationPackage examinationPackage, String note, State state) {
        this.id = id;
        this.billing = billing;
        this.doctor = doctor;
        this.patient = patient;
        this.reservationDate = reservationDate;
        this.contact = contact;
        this.specialization = specialization;
        this.payment = payment;
        this.paymentNote = paymentNote;
        this.examinationPackage = examinationPackage;
        this.note = note;
        this.state = state;
    }
}
