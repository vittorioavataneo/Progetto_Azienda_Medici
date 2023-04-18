package org.generation.italy.Progetto_Azienda_Medici.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import static org.generation.italy.Progetto_Azienda_Medici.dtos.MedicalExaminationDto.*;
import static org.generation.italy.Progetto_Azienda_Medici.utilities.StringUtilities.*;

@Getter
@Setter
@NoArgsConstructor
public class PatientDto extends PersonDto{

    private String taxCode;
    private Set<MedicalExaminationDto> medicalExaminationsDto;

    public PatientDto(long id, String firstname, String lastname, String dob, String sex,
                      String cellNumber, String email, String username, String password,
                      String taxCode, Set<MedicalExaminationDto> medicalExaminationsDto) {
        super(id, firstname, lastname, dob, sex, cellNumber, email, username, password);
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
                patient.getEmail(),
                patient.getUsername(),
                patient.getPassword(),
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
                this.getEmail(),
                fromStringToEnum(Sex.class, this.getSex()),
                this.getUsername(),
                this.getPassword(),
                this.getTaxCode(),
                this.getMedicalExaminationsDto()
                        .stream()
                        .map(MedicalExaminationDto::toMedicalExamination)
                        .collect(Collectors.toSet())
        );
    }
}
