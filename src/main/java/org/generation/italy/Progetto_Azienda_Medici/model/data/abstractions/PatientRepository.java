package org.generation.italy.Progetto_Azienda_Medici.model.data.abstractions;

import org.generation.italy.Progetto_Azienda_Medici.model.entities.Patient;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends GenericRepository<Patient> {
    @Query("""
            select p
            from Patient p
            where p.firstname like %:part% or p.lastname like %:part%
            """)
    Iterable<Patient> findByName(String part);


}
