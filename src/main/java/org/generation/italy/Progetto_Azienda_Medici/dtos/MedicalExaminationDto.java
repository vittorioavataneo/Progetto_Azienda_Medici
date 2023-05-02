package org.generation.italy.Progetto_Azienda_Medici.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.*;

import java.util.stream.StreamSupport;

import static org.generation.italy.Progetto_Azienda_Medici.utilities.StringUtilities.*;
import static org.generation.italy.Progetto_Azienda_Medici.utilities.StringUtilities.fromStringToEnum;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"doctorDto", "patientDto", "specializationDto"})
public class MedicalExaminationDto {

    private long id;
    private DoctorDto doctorDto;
    private PatientDto patientDto;
    private String doctorName;
    private String patientName;
    private String reservationDate;
    private String contact;
    private SpecializationDto specializationDto;
    private String specialization;
    private String payment;
    private String examinationPackage;
    private String note;
    private String paymentNote;
    private Boolean billing;
    private String state;

    public MedicalExaminationDto(long id, String doctorName, String patientName, String reservationDate,
                                 String contact, String specialization, String payment,
                                 String examinationPackage, String note, String paymentNote,
                                 Boolean billing, String state) {
        this.id = id;
        this.doctorName = doctorName;
        this.patientName = patientName;
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

    public MedicalExaminationDto(long id, DoctorDto doctorDto, PatientDto patientDto, String doctorName,  String patientName, String reservationDate,
                                 String contact, SpecializationDto specializationDto, String specialization, String payment,
                                 String examinationPackage, String note, String paymentNote,
                                 Boolean billing, String state) {
        this.id = id;
        this.doctorDto=doctorDto;
        this.patientDto=patientDto;
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.reservationDate = reservationDate;
        this.contact = contact;
        this.specializationDto = specializationDto;
        this.specialization = specialization;
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
                (me.getDoctor().getFirstname() + " "+me.getDoctor().getLastname()),
                (me.getPatient().getFirstname() + " "+me.getPatient().getLastname()),
                dateNullController(me.getReservationDate()),
                fromEnumToString(me.getContact()),
                me.getSpecialization().getSpecializationName(),
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

    public static Iterable<MedicalExaminationDto> fromExaminationIterable(Iterable<MedicalExamination> examinationIterable){
        return StreamSupport.stream(examinationIterable.spliterator(), false)
                .map(MedicalExaminationDto::fromMedicalExamination)
                .toList();
    }

}
