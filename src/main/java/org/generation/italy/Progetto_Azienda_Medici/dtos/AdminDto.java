package org.generation.italy.Progetto_Azienda_Medici.dtos;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Address;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Admin;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Sex;

import static org.generation.italy.Progetto_Azienda_Medici.utilities.StringUtilities.*;
import static org.generation.italy.Progetto_Azienda_Medici.utilities.StringUtilities.fromStringToEnum;

@Getter
@Setter
@NoArgsConstructor
public class AdminDto extends PersonDto{
    private String adminCode;
    private AddressDto addressDto;

    public AdminDto(long id, String firstname, String lastname, String dob, String sex,
                    String cellNumber, String email, String username, String password,
                    String adminCode, AddressDto addressDto) {
        super(id, firstname, lastname, dob, sex, cellNumber, email, username, password);
        this.adminCode = adminCode;
        this.addressDto = addressDto;
    }

    public static AdminDto fromAdmin(Admin admin){
        return new AdminDto(
                admin.getId(),
                admin.getFirstname(),
                admin.getLastname(),
                dateNullController(admin.getDob()),
                fromEnumToString(admin.getSex()),
                admin.getCellNumber(),
                admin.getEmail(),
                admin.getUsername(),
                admin.getPassword(),
                admin.getAdminCode(),
                AddressDto.fromAddress(admin.getAddress())
        );
    }

    public Admin toAdmin(){
        return new Admin(
                this.getId(),
                this.getFirstname(),
                this.getLastname(),
                fromJSONString(this.getDob()),
                this.getCellNumber(),
                this.getEmail(),
                fromStringToEnum(Sex.class, this.getSex()),
                this.getUsername(),
                this.getPassword(),
                this.getAdminCode(),
                this.getAddressDto().toAddress()
        );
    }
}
