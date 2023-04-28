package org.generation.italy.Progetto_Azienda_Medici.model.services.abstractions;

import org.generation.italy.Progetto_Azienda_Medici.model.entities.Doctor;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.MedicalExamination;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Patient;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Specialization;

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

}
