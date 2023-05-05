package org.generation.italy.Progetto_Azienda_Medici.model.data.abstractions;

import org.generation.italy.Progetto_Azienda_Medici.model.entities.MedicalExamination;
import org.springframework.data.jpa.repository.Query;

public interface MedicalExaminationRepository extends GenericRepository<MedicalExamination>, MedicalExaminationCustomRepository{

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

    @Query("""
        select m
        from MedicalExamination m join m.patient p
        where m.doctor.id = :id
            and (lower(p.firstname) like lower(concat('%', :name, '%'))
            or lower(p.lastname) like lower(concat('%', :name, '%')))
        """)
    Iterable<MedicalExamination> findAllMedicalExaminationByPatientNameOrSurnameIgnoreCase(String name, long id);

}
