package org.generation.italy.Progetto_Azienda_Medici.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Doctor;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Examination;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Sex;

@Getter
@Setter
@NoArgsConstructor
public class DoctorDto extends PersonDto{

    private String username;
    private AddressDto address;
    private Examination examination;
    private boolean billing;

    public DoctorDto(long id, String firstname, String lastname, String cellNumber,
                     String email, Sex sex, String username, AddressDto address,
                     Examination examination, boolean billing) {
        super(id, firstname, lastname, sex, cellNumber, email);
        this.username = username;
        this.address = address;
        this.examination = examination;
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
                doctor.getExamination(),
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
                null, // La password non viene fornita dal DTO per questioni di privacy ma da rivedere
                this.getAddress().toAddress(),
                this.getExamination(),
                this.isBilling()
        );
    }
}

