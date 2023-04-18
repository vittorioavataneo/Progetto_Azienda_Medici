package org.generation.italy.Progetto_Azienda_Medici.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.*;

import java.time.LocalDate;

import static org.generation.italy.Progetto_Azienda_Medici.utilities.StringUtilities.*;

@Getter
@Setter
@NoArgsConstructor
public class PatientDto extends PersonDto{
    private DoctorDto doctor;
    private String reservationDate;
    private String contact;
    private SpecializationDto specialization;
    private String payment;
    private String examinationPackage;
    private String note;
    private String paymentNote;
    private boolean billing;
    private String state;

    public PatientDto(long id, String firstname, String lastname, String dob, String sex, String cellNumber, String email, DoctorDto doctor,
                      String reservationDate, String contact, SpecializationDto specialization, String payment,
                      String examinationPackage, String note, String paymentNote, boolean billing, String state) {
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
                dateNullController(patient.getDob()),
                fromEnumToString(patient.getSex()),
                patient.getCellNumber(),
                patient.getEmail(),
                DoctorDto.fromDoctor(patient.getDoctor()),
                dateNullController(patient.getReservationDate()),
                fromEnumToString(patient.getContact()),
                SpecializationDto.fromSpecialization(patient.getSpecialization()),
                fromEnumToString(patient.getPayment()),
                fromEnumToString(patient.getExaminationPackage()),
                patient.getNote(),
                patient.getPaymentNote(),
                patient.isBilling(),
                fromEnumToString(patient.getState()));
    }

    public Patient toPatient() {
        return new Patient(
                this.getId(),
                this.getFirstname(),
                this.getLastname(),
                fromJSONString(this.getDob()),
                fromStringToEnum(Sex.class, this.getSex()),
                this.getCellNumber(),
                this.getEmail(),
                this.getDoctor().toDoctor(),
                fromJSONString(this.getReservationDate()),
                fromStringToEnum(Contact.class, this.getContact()),
                this.getSpecialization().toSpecialization(),
                fromStringToEnum(Payment.class, this.getPayment()),
                fromStringToEnum(ExaminationPackage.class, this.getExaminationPackage()),
                this.getNote(),
                this.getPaymentNote(),
                this.isBilling(),
                fromStringToEnum(State.class, this.getState()));
    }
}
