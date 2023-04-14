package org.generation.italy.Progetto_Azienda_Medici.model.services.abstractions;

import org.generation.italy.Progetto_Azienda_Medici.model.entities.Doctor;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Examination;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Patient;

public interface AbstractDidacticService {
    //DOCTOR
    Iterable<Doctor> findDoctorByName(String name);
    Iterable<Doctor> findDoctorByExamination(Examination examination);

    //PATIENT
    Iterable<Patient> findPatientByName(String name);


}
