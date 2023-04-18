package org.generation.italy.Progetto_Azienda_Medici.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Doctor;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Sex;

import static org.generation.italy.Progetto_Azienda_Medici.utilities.StringUtilities.*;

@Getter
@Setter
@NoArgsConstructor
public class DoctorDto extends PersonDto{

    private String username;
    private String password;
    private AddressDto address;
    private SpecializationDto specialization;
    private boolean billing;

    public DoctorDto(long id, String firstname, String lastname, String dob, String cellNumber,
                     String email, String sex, String username, AddressDto address,
                     SpecializationDto specialization, boolean billing) {
        super(id, firstname, lastname, dob, sex, cellNumber, email);
        this.username = username;
        this.address = address;
        this.specialization = specialization;
        this.billing = billing;
    }

    public static DoctorDto fromDoctor(Doctor doctor) {
        return new DoctorDto(
                doctor.getId(),
                doctor.getFirstname(),
                doctor.getLastname(),
                doctor.getDob() != null ? doctor.getDob().toString() : "",
                doctor.getCellNumber(),
                doctor.getEmail(),
                fromEnumToString(doctor.getSex()),
                doctor.getUsername(),
                AddressDto.fromAddress(doctor.getAddress()),
                SpecializationDto.fromSpecialization(doctor.getSpecialization()),
                doctor.isBilling()
        );
    }

    public Doctor toDoctor() {
        return new Doctor(
                this.getId(),
                this.getFirstname(),
                this.getLastname(),
                fromJSONString(this.dob),
                fromStringToEnum(Sex.class, this.getSex()),
                this.getCellNumber(),
                this.getEmail(),
                this.getUsername(),
                this.getPassword(),
                this.getAddress().toAddress(),
                this.getSpecialization().toSpecialization(),
                this.isBilling()
        );
    }
}

