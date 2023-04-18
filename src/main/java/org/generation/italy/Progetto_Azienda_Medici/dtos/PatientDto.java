package org.generation.italy.Progetto_Azienda_Medici.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.*;

import java.time.LocalDate;

import static org.generation.italy.Progetto_Azienda_Medici.utilities.StringUtilities.fromJSONString;

@Getter
@Setter
@NoArgsConstructor
public class PatientDto extends PersonDto{
    private DoctorDto doctor;
    private String reservationDate;
    private Contact contact;
    private SpecializationDto specialization;
    private Payment payment;
    private ExaminationPackage examinationPackage;
    private String note;
    private String paymentNote;
    private boolean billing;
    private State state;

    public PatientDto(long id, String firstname, String lastname, String dob, Sex sex, String cellNumber, String email, DoctorDto doctor,
                      String reservationDate, Contact contact, SpecializationDto specialization, Payment payment,
                      ExaminationPackage examinationPackage, String note, String paymentNote, boolean billing, State state) {
        super(id, firstname, lastname, String.valueOf(dob), sex, cellNumber, email);
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

    public static PatientDto fromPatient(Patient patient) {
        return new PatientDto(
                patient.getId(),
                patient.getFirstname(),
                patient.getLastname(),
                patient.getDob() != null ? patient.getDob().toString() : "",
                patient.getSex(),
                patient.getCellNumber(),
                patient.getEmail(),
                DoctorDto.fromDoctor(patient.getDoctor()),
                patient.getReservationDate() != null ? patient.getReservationDate().toString() : "",
                patient.getContact(),
                SpecializationDto.fromSpecialization(patient.getSpecialization()),
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
                fromJSONString(this.getDob()),
                this.getSex(),
                this.getCellNumber(),
                this.getEmail(),
                this.getDoctor().toDoctor(),
                fromJSONString(this.getReservationDate()),
                this.getContact(),
                this.getSpecialization().toSpecialization(),
                this.getPayment(),
                this.getExaminationPackage(),
                this.getNote(),
                this.getPaymentNote(),
                this.isBilling(),
                this.getState());
    }
}
