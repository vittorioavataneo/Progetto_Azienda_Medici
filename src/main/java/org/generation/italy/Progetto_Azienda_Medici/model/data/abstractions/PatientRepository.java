package org.generation.italy.Progetto_Azienda_Medici.model.data.abstractions;

import org.generation.italy.Progetto_Azienda_Medici.model.entities.Patient;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PatientRepository extends GenericRepository<Patient> {
    @Query("""
            select p
            from MedicalExamination m join m.patient p
            where m.doctor.id = :id
                and (lower(p.firstname) like lower(concat('%', :part, '%'))
                or lower(p.lastname) like lower(concat('%', :part, '%')))
            """)
    Iterable<Patient> findByName(String part, long id);

    @Query("""
            select distinct m.patient
            from MedicalExamination m
            where m.doctor.id = :id
            """)
    Iterable<Patient> findAllPatientByDoctorId(long id);

    @Query("""
            select distinct m.patient
            from MedicalExamination m
            where m.patient.id = :id
            """)
    Optional<Patient> findPatientByExamId(long id);
}
