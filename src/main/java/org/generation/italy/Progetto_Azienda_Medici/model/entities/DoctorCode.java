package org.generation.italy.Progetto_Azienda_Medici.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "codice_medici")
@Getter
@Setter
@NoArgsConstructor
public class DoctorCode {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "codice_medici_generator")
    @SequenceGenerator(name="codice_medici_generator", sequenceName = "id_codice_medici_sequence", allocationSize=1)
    @Column(name = "id_codice_medici")
    private long id;

    @Column(name = "codice", nullable = false, length = 5)
    private String code;

    public DoctorCode(long id, String code) {
        this.id = id;
        this.code = code;
    }
}