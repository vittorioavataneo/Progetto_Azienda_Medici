package org.generation.italy.Progetto_Azienda_Medici.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "fatturazione")
@Getter
@Setter
@NoArgsConstructor
public class Billing {
    @Id
    @GeneratedValue(generator = "fatturazione_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "fatturazione_generator", sequenceName = "fatturazione_sequence", allocationSize = 1)
    @Column(name= "id_fatturazione")
    private long id;
    @ManyToOne
    @JoinColumn(name = "id_indirizzo")
    private Address address;
    @Column(name = "codice_fiscale")
    private String taxId;
    @Column(name = "partita_iva")
    private String vat;

    public Billing(long id, Address address, String taxId, String vat) {
        this.id = id;
        this.address = address;
        this.taxId = taxId;
        this.vat = vat;
    }
}
