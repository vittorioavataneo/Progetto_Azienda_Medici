package org.generation.italy.Progetto_Azienda_Medici.dtos;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Address;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Admin;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Sex;
import org.generation.italy.Progetto_Azienda_Medici.security.user.User;

import static org.generation.italy.Progetto_Azienda_Medici.utilities.StringUtilities.*;
import static org.generation.italy.Progetto_Azienda_Medici.utilities.StringUtilities.fromStringToEnum;

@Getter
@Setter
@NoArgsConstructor
public class AdminDto extends PersonDto{

    private String adminCode;

    public AdminDto(long id, String firstname, String lastname, String dob, String sex, String cellNumber, User user, String adminCode) {
        super(id, firstname, lastname, dob, sex, cellNumber, user);
        this.adminCode = adminCode;
    }

    public static AdminDto fromAdmin(Admin admin){
        return new AdminDto(
                admin.getId(),
                admin.getFirstname(),
                admin.getLastname(),
                dateNullController(admin.getDob()),
                fromEnumToString(admin.getSex()),
                admin.getCellNumber(),
                admin.getUser(),
                admin.getAdminCode()
        );
    }

    public Admin toAdmin(){
        return new Admin(
                this.getId(),
                this.getFirstname(),
                this.getLastname(),
                fromJSONString(this.getDob()),
                this.getCellNumber(),
                fromStringToEnum(Sex.class, this.getSex()),
                this.getUser(),
                this.getAdminCode()
        );
    }
}
