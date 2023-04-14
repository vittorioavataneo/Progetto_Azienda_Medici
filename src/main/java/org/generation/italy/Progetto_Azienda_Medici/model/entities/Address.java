package org.generation.italy.Progetto_Azienda_Medici.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "indirizzo")
@Getter
@Setter
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(generator = "indirizzo_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "indirizzo_generator", sequenceName = "indirizzo_sequence", allocationSize = 1)
    @Column(name= "id_indirizzo")
    private long id;
    @Column(name = "via")
    private String street;
    private String cap;
    @Column(name = "città")
    private String city;
    @Column(name = "provincia")
    private String province;
    @Column(name = "nazione")
    private String country;

    public Address(long id, String street, String cap, String city, String province, String country) {
        this.id = id;
        this.street = street;
        this.cap = cap;
        this.city = city;
        this.province = province;
        this.country = country;
    }
}
