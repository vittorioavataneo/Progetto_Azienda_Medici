package org.generation.italy.Progetto_Azienda_Medici.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.*;

import static org.generation.italy.Progetto_Azienda_Medici.utilities.StringUtilities.*;
import static org.generation.italy.Progetto_Azienda_Medici.utilities.StringUtilities.fromStringToEnum;

@Getter
@Setter
@NoArgsConstructor
public class MedicalExaminationDto {
    private long id;
    private DoctorDto doctorDto;
    private PatientDto patientDto;
    private String reservationDate;
    private String contact;
    private SpecializationDto specializationDto;
    private String payment;
    private String examinationPackage;
    private String note;
    private String paymentNote;
    private Boolean billing;
    private String state;

    public MedicalExaminationDto(long id, DoctorDto doctorDto, PatientDto patientDto, String reservationDate,
                                 String contact, SpecializationDto specializationDto, String payment,
                                 String examinationPackage, String note, String paymentNote,
                                 Boolean billing, String state) {
        this.id = id;
        this.doctorDto = doctorDto;
        this.patientDto = patientDto;
        this.reservationDate = reservationDate;
        this.contact = contact;
        this.specializationDto = specializationDto;
        this.payment = payment;
        this.examinationPackage = examinationPackage;
        this.note = note;
        this.paymentNote = paymentNote;
        this.billing = billing;
        this.state = state;
    }

    public static MedicalExaminationDto fromMedicalExamination(MedicalExamination me) {
        return new MedicalExaminationDto(
                me.getId(),
                DoctorDto.fromDoctor(me.getDoctor()),
                PatientDto.fromPatient(me.getPatient()),
                dateNullController(me.getReservationDate()),
                fromEnumToString(me.getContact()),
                SpecializationDto.fromSpecialization(me.getSpecialization()),
                fromEnumToString(me.getPayment()),
                fromEnumToString(me.getExaminationPackage()),
                me.getNote(),
                me.getPaymentNote(),
                me.isBilling(),
                fromEnumToString(me.getState()));
    }

    public MedicalExamination toMedicalExamination() {
        return new MedicalExamination(
                this.getId(),
                this.getBilling(),
                this.getDoctorDto().toDoctor(),
                this.getPatientDto().toPatient(),
                fromJSONString(this.getReservationDate()),
                fromStringToEnum(Contact.class, this.getContact()),
                this.getSpecializationDto().toSpecialization(),
                fromStringToEnum(Payment.class, this.getPayment()),
                this.getPaymentNote(),
                fromStringToEnum(ExaminationPackage.class, this.getExaminationPackage()),
                this.getNote(),
                fromStringToEnum(State.class, this.getState()));
    }

}
