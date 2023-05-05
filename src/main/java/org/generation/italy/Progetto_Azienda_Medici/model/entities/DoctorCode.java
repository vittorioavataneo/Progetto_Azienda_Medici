package org.generation.italy.Progetto_Azienda_Medici.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "codice_medici")
public class DoctorCode {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "codice_medici_generator")
    @SequenceGenerator(name="codice_medici_generator", sequenceName = "id_codice_medici_sequence", allocationSize=1)
    @Column(name = "id_codice_medici")
    private Long idCodiceMedici;

    @Column(name = "codice", nullable = false, length = 5)
    private String codice;

    // Costruttori, getter e setter

}