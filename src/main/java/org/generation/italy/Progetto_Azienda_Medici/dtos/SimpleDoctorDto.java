package org.generation.italy.Progetto_Azienda_Medici.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Doctor;

import java.util.stream.StreamSupport;

@Getter
@Setter
@NoArgsConstructor
public class SimpleDoctorDto extends SimplePersonDto{

    public SimpleDoctorDto(long id, String firstname, String lastname) {
        super(id,firstname, lastname);
    }

    public static SimpleDoctorDto fromSimpleDoctor(Doctor doctor){
        return new SimpleDoctorDto(
                doctor.getId(),
                doctor.getFirstname(),
                doctor.getLastname());
    }

    public static Iterable<SimpleDoctorDto> fromSimpleDoctorIterable(Iterable<Doctor> doctorIterable){
        return StreamSupport.stream(doctorIterable.spliterator(), false)
                .map(SimpleDoctorDto::fromSimpleDoctor)
                .toList();
    }
}
