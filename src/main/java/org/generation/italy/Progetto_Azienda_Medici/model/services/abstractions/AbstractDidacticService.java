package org.generation.italy.Progetto_Azienda_Medici.model.services.abstractions;

import org.generation.italy.Progetto_Azienda_Medici.model.entities.Doctor;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.MedicalExamination;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Patient;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Specialization;

import java.util.Optional;

public interface AbstractDidacticService {
    //DOCTOR
    Iterable<Doctor> findDoctorByName(String part);
    Iterable<Doctor> findDoctorBySpecialization(String part);

    //PATIENT
    Iterable<Patient> findPatientByName(String part);
    Iterable<Patient> findAllPatientByDoctorId(long id);

    //MEDICAL EXAMINATION
    Iterable<MedicalExamination> findAllMedicalExaminationByDoctorId(long id);
    Iterable<MedicalExamination> findAllMedicalExaminationByPatientId(long id);
    void changeMedicalExaminationToNull(long id);
    void changeMedicalExaminationToProgrammed(long id);

    //USER
    Optional<Long> findPersonIdByUserEmail(String email);

    //SPECIALIZATION
    Optional<Specialization> findSpecializationBySpecializationName(String specializationName);

}
