package org.generation.italy.Progetto_Azienda_Medici.model.data.abstractions;

import org.generation.italy.Progetto_Azienda_Medici.model.entities.MedicalExamination;
import org.springframework.data.jpa.repository.Query;

public interface MedicalExaminationRepository extends GenericRepository<MedicalExamination>{

    @Query("""
            select me
            from MedicalExamination me join me.doctor d
            where d.id = :id
            """)
    Iterable<MedicalExamination> findAllMedicalExaminationByDoctorId(long id);

    @Query("""
            select me
            from MedicalExamination me join me.patient p
            where p.id = :id
            """)
    Iterable<MedicalExamination> findAllMedicalExaminationByPatientId(long id);

}
