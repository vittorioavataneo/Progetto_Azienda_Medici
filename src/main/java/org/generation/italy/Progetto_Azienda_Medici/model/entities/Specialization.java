package org.generation.italy.Progetto_Azienda_Medici.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "visita_specialistica")
@Getter
@Setter
@NoArgsConstructor
public class Specialization {

    @Id
    @GeneratedValue(generator = "visita_specialistica_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "visita_specialistica_generator", sequenceName = "visita_specialistica_sequence", allocationSize = 1)
    @Column(name= "id_visita_specialistica")
    private long id;
    @Column(name = "specialistica")
    private String specializationName;

    public Specialization(long id, String specializationName) {
        this.id = id;
        this.specializationName = specializationName;
    }
}
