package org.generation.italy.Progetto_Azienda_Medici.model.services.implementations;

import org.generation.italy.Progetto_Azienda_Medici.model.data.abstractions.AdminRepository;
import org.generation.italy.Progetto_Azienda_Medici.model.data.abstractions.DoctorRepository;
import org.generation.italy.Progetto_Azienda_Medici.model.data.abstractions.PatientRepository;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Doctor;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Patient;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Specialization;
import org.generation.italy.Progetto_Azienda_Medici.model.services.abstractions.AbstractDidacticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StandardDidacticService implements AbstractDidacticService{

    private DoctorRepository doctorRepo;
    private PatientRepository patientRepo;
    private AdminRepository adminRepository;

    @Autowired
    public StandardDidacticService(DoctorRepository doctorRepo, PatientRepository patientRepo,
                                   AdminRepository adminRepository) {
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
        this.adminRepository = adminRepository;
    }

    //DOCTOR
    @Override
    public Iterable<Doctor> findDoctorByName(String part) {
        return doctorRepo.findByName(part);
    }
    @Override
    public Iterable<Doctor> findDoctorBySpecialization(String part) {
        return doctorRepo.findBySpecialization(part);
    }

    //PATIENT
    @Override
    public Iterable<Patient> findPatientByName(String part) { return patientRepo.findByName(part); }

}
