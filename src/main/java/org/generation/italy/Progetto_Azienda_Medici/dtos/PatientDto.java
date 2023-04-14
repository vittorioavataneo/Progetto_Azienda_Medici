package org.generation.italy.Progetto_Azienda_Medici.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class PatientDto extends PersonDto{
    private DoctorDto doctor;
    private LocalDate reservationDate;
    private Contact contact;
    private Examination examination;
    private Payment payment;
    private ExaminationPackage examinationPackage;
    private String note;
    private String paymentNote;
    private boolean billing;
    private State state;

    public PatientDto(long id, String firstname, String lastname, Sex sex, String cellNumber, String email, DoctorDto doctor,
                      LocalDate reservationDate, Contact contact, Examination examination, Payment payment,
                      ExaminationPackage examinationPackage, String note, String paymentNote, boolean billing, State state) {
        super(id, firstname, lastname, sex, cellNumber, email);
        this.doctor = doctor;
        this.reservationDate = reservationDate;
        this.contact = contact;
        this.examination = examination;
        this.payment = payment;
        this.examinationPackage = examinationPackage;
        this.note = note;
        this.paymentNote = paymentNote;
        this.billing = billing;
        this.state = state;
    }

    public static PatientDto fromPatient(Patient patient) {
        return new PatientDto(
                patient.getId(),
                patient.getFirstname(),
                patient.getLastname(),
                patient.getSex(),
                patient.getCellNumber(),
                patient.getEmail(),
                DoctorDto.fromDoctor(patient.getDoctor()),
                patient.getReservationDate(),
                patient.getContact(),
                patient.getExamination(),
                patient.getPayment(),
                patient.getExaminationPackage(),
                patient.getNote(),
                patient.getPaymentNote(),
                patient.isBilling(),
                patient.getState());
    }

    public Patient toPatient() {
        return new Patient(
                this.getId(),
                this.getFirstname(),
                this.getLastname(),
                this.getSex(),
                this.getCellNumber(),
                this.getEmail(),
                this.getDoctor().toDoctor(),
                this.getReservationDate(),
                this.getContact(),
                this.getExamination(),
                this.getPayment(),
                this.getExaminationPackage(),
                this.getNote(),
                this.getPaymentNote(),
                this.isBilling(),
                this.getState());
    }
}
