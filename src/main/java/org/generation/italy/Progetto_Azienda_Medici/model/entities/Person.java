package org.generation.italy.Progetto_Azienda_Medici.model.entities;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.italy.Progetto_Azienda_Medici.security.user.User;
import org.hibernate.annotations.Type;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "persona")
@Getter
@Setter
@NoArgsConstructor
public abstract class Person {
    @Id
    @GeneratedValue(generator = "persona_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "persona_generator", sequenceName = "persona_sequence", allocationSize = 1)
    @Column(name= "id_persona")
    protected long id;

    @Column(name = "nome")
    protected String firstname;

    @Column(name = "cognome")
    protected String lastname;

    @Column(name = "data_di_nascita")
    protected LocalDate dob;

    @Column(name = "telefono")
    protected String cellNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "sesso")
    @Type(PostgreSQLEnumType.class)
    protected Sex sex;

    @OneToOne
    @JoinColumn(name = "id_user_p")
    protected User user;

    public Person(long id, String firstname, String lastname, LocalDate dob,
                  String cellNumber, Sex sex, User user) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.cellNumber = cellNumber;
        this.sex = sex;
        this.user = user;
    }
}
