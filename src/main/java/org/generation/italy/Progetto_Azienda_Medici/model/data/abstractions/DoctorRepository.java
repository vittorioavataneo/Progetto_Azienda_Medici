package org.generation.italy.Progetto_Azienda_Medici.model.data.abstractions;

import org.generation.italy.Progetto_Azienda_Medici.model.entities.Doctor;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Specialization;
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
            from Doctor d join d.specialization s
            where s.specializationName like :part
            """)
    Iterable<Doctor> findBySpecialization(String part);

}
