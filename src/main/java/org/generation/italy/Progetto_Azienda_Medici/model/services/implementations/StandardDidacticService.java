package org.generation.italy.Progetto_Azienda_Medici.model.services.implementations;

import org.generation.italy.Progetto_Azienda_Medici.model.data.abstractions.AdminRepository;
import org.generation.italy.Progetto_Azienda_Medici.model.data.abstractions.DoctorRepository;
import org.generation.italy.Progetto_Azienda_Medici.model.data.abstractions.MedicalExaminationRepository;
import org.generation.italy.Progetto_Azienda_Medici.model.data.abstractions.PatientRepository;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Doctor;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.MedicalExamination;
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
    private MedicalExaminationRepository examinationRepository;

    @Autowired
    public StandardDidacticService(DoctorRepository doctorRepo, PatientRepository patientRepo,
                                   AdminRepository adminRepository, MedicalExaminationRepository examinationRepository) {
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
        this.adminRepository = adminRepository;
        this.examinationRepository = examinationRepository;
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

    @Override
    public Iterable<Patient> findAllPatientByDoctorId(long id) {
        return patientRepo.findAllPatientByDoctorId(id);
    }


    //MEDICAL EXAMINATION
    @Override
    public Iterable<MedicalExamination> findAllMedicalExaminationByDoctorId(long id) {
        return examinationRepository.findAllMedicalExaminationByDoctorId(id);
    }

    @Override
    public Iterable<MedicalExamination> findAllMedicalExaminationByPatientId(long id) {
        return examinationRepository.findAllMedicalExaminationByPatientId(id);
    }

}
