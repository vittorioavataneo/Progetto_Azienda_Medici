package org.generation.italy.Progetto_Azienda_Medici.model.entities;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

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
    @Column(name = "telefono")
    protected String cellNumber;
    protected String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "sesso")
    @Type(PostgreSQLEnumType.class)
    protected Sex sex;

    public Person(long id, String firstname, String lastname, Sex sex, String cellNumber, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.sex = sex;
        this.cellNumber = cellNumber;
        this.email = email;

    }
}
