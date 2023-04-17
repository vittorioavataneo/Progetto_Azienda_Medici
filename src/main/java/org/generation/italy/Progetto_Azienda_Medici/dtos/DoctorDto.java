package org.generation.italy.Progetto_Azienda_Medici.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Doctor;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Sex;

@Getter
@Setter
@NoArgsConstructor
public class DoctorDto extends PersonDto{

    private String username;
    private String password;
    private AddressDto address;
    private SpecializationDto specialization;
    private boolean billing;

    public DoctorDto(long id, String firstname, String lastname, String cellNumber,
                     String email, Sex sex, String username, AddressDto address,
                     SpecializationDto specialization, boolean billing) {
        super(id, firstname, lastname, sex, cellNumber, email);
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
                doctor.getCellNumber(),
                doctor.getEmail(),
                doctor.getSex(),
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
                this.getSex(),
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

