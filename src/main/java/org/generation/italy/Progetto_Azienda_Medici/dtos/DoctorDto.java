package org.generation.italy.Progetto_Azienda_Medici.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Doctor;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Sex;
import org.generation.italy.Progetto_Azienda_Medici.security.user.User;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.generation.italy.Progetto_Azienda_Medici.utilities.StringUtilities.*;

@Getter
@Setter
@NoArgsConstructor
public class DoctorDto extends PersonDto{

    private AddressDto address;
    private String doctorCode;
    private SpecializationDto specialization;
    private Set<MedicalExaminationDto> medicalExaminationsDto;

    public DoctorDto(long id, String firstname, String lastname, String dob,
                     String sex, String cellNumber, User user, AddressDto address,
                     String doctorCode, SpecializationDto specialization, Set<MedicalExaminationDto> medicalExaminationsDto) {
        super(id, firstname, lastname, dob, sex, cellNumber, user);
        this.address = address;
        this.doctorCode = doctorCode;
        this.specialization = specialization;
        this.medicalExaminationsDto = medicalExaminationsDto;
    }

    public static DoctorDto fromDoctor(Doctor doctor) {
        return new DoctorDto(
                doctor.getId(),
                doctor.getFirstname(),
                doctor.getLastname(),
                dateNullController(doctor.getDob()),
                fromEnumToString(doctor.getSex()),
                doctor.getCellNumber(),
                doctor.getUser(),
                AddressDto.fromAddress(doctor.getAddress()),
                doctor.getDoctorCode(),
                SpecializationDto.fromSpecialization(doctor.getSpecialization()),
                doctor.getMedicalExaminations()
                        .stream()
                        .map(MedicalExaminationDto::fromMedicalExamination)
                        .collect(Collectors.toSet())
        );
    }

    public Doctor toDoctor() {
        return new Doctor(
                this.getId(),
                this.getFirstname(),
                this.getLastname(),
                fromJSONString(this.dob),
                this.getCellNumber(),
                fromStringToEnum(Sex.class, this.getSex()),
                this.getUser(),
                this.getAddress().toAddress(),
                this.getDoctorCode(),
                this.getSpecialization().toSpecialization(),
                this.getMedicalExaminationsDto()
                        .stream()
                        .map(MedicalExaminationDto::toMedicalExamination)
                        .collect(Collectors.toSet())
        );
    }

    public static Iterable<DoctorDto> fromDoctorIterable(Iterable<Doctor> doctorIterable){

        return StreamSupport.stream(doctorIterable.spliterator(), false)
                .map(DoctorDto::fromDoctor)
                .toList();
    }
}

