package org.generation.italy.Progetto_Azienda_Medici.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.*;
import org.generation.italy.Progetto_Azienda_Medici.security.user.User;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.generation.italy.Progetto_Azienda_Medici.dtos.MedicalExaminationDto.*;
import static org.generation.italy.Progetto_Azienda_Medici.utilities.StringUtilities.*;

@Getter
@Setter
@NoArgsConstructor
public class PatientDto extends PersonDto{

    private String taxCode;

    private Set<MedicalExaminationDto> medicalExaminationsDto;

    public PatientDto(long id, String firstname, String lastname, String dob, String sex, String cellNumber,
                      String taxCode, Set<MedicalExaminationDto> medicalExaminationsDto) {
        super(id, firstname, lastname, dob, sex, cellNumber);
        this.taxCode = taxCode;
        this.medicalExaminationsDto = medicalExaminationsDto;
    }

    public PatientDto(long id, String firstname, String lastname, String dob, String sex, String cellNumber,
                      User user, String taxCode, Set<MedicalExaminationDto> medicalExaminationsDto) {
        super(id, firstname, lastname, dob, sex, cellNumber, user);
        this.taxCode = taxCode;
        this.medicalExaminationsDto = medicalExaminationsDto;
    }

    public static PatientDto fromPatient(Patient patient) {
        return new PatientDto(
                patient.getId(),
                patient.getFirstname(),
                patient.getLastname(),
                dateNullController(patient.getDob()),
                fromEnumToString(patient.getSex()),
                patient.getCellNumber(),
                patient.getTaxCode(),
                patient.getMedicalExaminations()
                        .stream()
                        .map(MedicalExaminationDto::fromMedicalExamination)
                        .collect(Collectors.toSet())
        );
    }

    public Patient toPatient() {
        return new Patient(
                this.getId(),
                this.getFirstname(),
                this.getLastname(),
                fromJSONString(this.getDob()),
                this.getCellNumber(),
                fromStringToEnum(Sex.class, this.getSex()),
                this.getUser(),
                this.getTaxCode(),
                this.getMedicalExaminationsDto()
                        .stream()
                        .map(MedicalExaminationDto::toMedicalExamination)
                        .collect(Collectors.toSet())
        );
    }

    public static Iterable<PatientDto> fromPatientIterable(Iterable<Patient> patientIterable){
        return StreamSupport.stream(patientIterable.spliterator(), false)
                .map(PatientDto::fromPatient)
                .toList();
    }

}
