package org.generation.italy.Progetto_Azienda_Medici.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.generation.italy.Progetto_Azienda_Medici.dtos.MedicalExaminationDto;
import org.generation.italy.Progetto_Azienda_Medici.dtos.PatientDto;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Admin;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Doctor;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.MedicalExamination;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Patient;
import org.generation.italy.Progetto_Azienda_Medici.model.services.abstractions.AbstractDidacticService;
import org.generation.italy.Progetto_Azienda_Medici.model.services.implementations.GenericService;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "personalArea/doctor")
public class DoctorController {


    private AbstractDidacticService didacticService;
    private GenericService<Patient> genericServicePatient;
    private GenericService<MedicalExamination> medicalExaminationService;

    @Autowired
    public DoctorController(AbstractDidacticService didacticService, GenericService<Patient> genericServicePatient, GenericService<MedicalExamination> medicalExaminationService) {
        this.didacticService = didacticService;
        this.genericServicePatient = genericServicePatient;
        this.medicalExaminationService = medicalExaminationService;
    }

    // DOCTORS METHODS

    // Creation Patient
    @PostMapping()
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto){

        Patient patient = patientDto.toPatient();
        var resultPatient = this.genericServicePatient.create(patient);
        return ResponseEntity.created(URI.create("personalArea/doctor"+patient.getId())).body(PatientDto.fromPatient(resultPatient));
    }

    // Insert Patient
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePatient(@RequestBody PatientDto patientDto,
                                              @PathVariable long id){
        if (patientDto.getId() != id){
            return ResponseEntity.badRequest().build();
        }
        Patient patient = patientDto.toPatient();
        try {
            this.genericServicePatient.update(patient);
            return ResponseEntity.noContent().build();
        } catch (DataException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        } catch (EntityNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    // Delete Patient
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatientById(@PathVariable long id){
        this.genericServicePatient.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    // Find all his Patients
    @GetMapping()
    public ResponseEntity<Iterable<PatientDto>> findAllDoctorPatients(String part){

        Iterable<Patient> patientIterable = this.didacticService.findPatientByName(part);
        return ResponseEntity.ok().body(PatientDto.fromPatientIterable(patientIterable));
    }




    // DOCTOR'S MEDICAL EXAMINATIONS METHODS


    // Create new medical Examination
    @PostMapping()
    public ResponseEntity<MedicalExaminationDto> createMedicalExamination(@RequestBody MedicalExaminationDto medicalExaminationDto){

        MedicalExamination mD = medicalExaminationDto.toMedicalExamination();
        var resultMedicalExamination = this.medicalExaminationService.create(mD);
        return ResponseEntity.created(URI.create("personalArea/doctor"+mD.getId())).body(MedicalExaminationDto.fromMedicalExamination(resultMedicalExamination));
    }

    // Delete Patient medical examination
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalExamination(@PathVariable long id){
        this.medicalExaminationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    // find all the medical examinations

    /*
    ************************************
    * ********************************
    * ***********************************
    * *********************************
    * ******************************
     */


}



















