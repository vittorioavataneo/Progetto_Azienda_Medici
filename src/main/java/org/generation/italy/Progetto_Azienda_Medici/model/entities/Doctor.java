package org.generation.italy.Progetto_Azienda_Medici.model.entities;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Entity
@PrimaryKeyJoinColumn(name = "id_teacher")
@Table(name = "teacher")
@Getter
@Setter
@NoArgsConstructor
public class Doctor extends Person{

    public String username;
    public String password;
    @ManyToOne
    @JoinColumn(name = "id_indirizzo")
    private Address address;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "visita")
    @Type(PostgreSQLEnumType.class)
    private Examination examination;
    @Column(name = "fatturazione")
    private boolean billing;

    public Doctor(long id, String firstname, String lastname, Sex sex, String cellNumber,
                  String email, String username, String password,
                  Address address, Examination examination, boolean billing) {
        super(id, firstname, lastname, sex, cellNumber, email);
        this.username = username;
        this.password = password;
        this.address = address;
        this.examination = examination;
        this.billing = billing;
    }
}
