package org.generation.italy.Progetto_Azienda_Medici.model.data.abstractions;

import org.generation.italy.Progetto_Azienda_Medici.model.entities.Doctor;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query("""
            select d
            from Doctor d
            where d.firstname like :part or d.lastname like: part
            """)
    Iterable<Doctor> findByName(String part);

    @Query("""
            select d
            from Doctor d
            where d.examination = :examination
            """)
    Iterable<Doctor> findByExamination(Examination examination);

}
