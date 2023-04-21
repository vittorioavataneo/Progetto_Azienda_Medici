package org.generation.italy.Progetto_Azienda_Medici.controllers;

import org.generation.italy.Progetto_Azienda_Medici.dtos.DoctorDto;
import org.generation.italy.Progetto_Azienda_Medici.dtos.MedicalExaminationDto;
import org.generation.italy.Progetto_Azienda_Medici.dtos.SpecializationDto;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Admin;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Doctor;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.MedicalExamination;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Patient;
import org.generation.italy.Progetto_Azienda_Medici.model.services.abstractions.AbstractDidacticService;
import org.generation.italy.Progetto_Azienda_Medici.model.services.implementations.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "personalArea/patient")
public class PatientController {

    private AbstractDidacticService didacticService;
    private GenericService<Doctor> genericServiceDoctor;
    private GenericService<MedicalExamination> genericServiceME;

    @Autowired
    public PatientController(AbstractDidacticService didacticService, GenericService<Doctor> genericServiceDoctor, GenericService<MedicalExamination> genericServiceME) {
        this.didacticService = didacticService;
        this.genericServiceDoctor = genericServiceDoctor;
        this.genericServiceME = genericServiceME;
    }


    // Create new medical Examination
    @PostMapping()
    public ResponseEntity<MedicalExaminationDto> createMedicalExamination(@RequestBody MedicalExaminationDto medicalExaminationDto){

        MedicalExamination mD = medicalExaminationDto.toMedicalExamination();
        var resultMedicalExamination = this.genericServiceME.create(mD);
        return ResponseEntity.created(URI.create("personalArea/patient"+mD.getId())).body(MedicalExaminationDto.fromMedicalExamination(resultMedicalExamination));
    }

    // Find Doctor by specialization
    @GetMapping
    public ResponseEntity<Iterable<DoctorDto>> findDoctorBySpecialization(String part){

        Iterable<Doctor> doctor = didacticService.findDoctorBySpecialization(part);
        return ResponseEntity.ok().body(DoctorDto.fromDoctorIterable(doctor));
    }




    // find all the medical examinations

    /*
     ************************************
     * ********************************
     * ***********************************
     * *********************************
     * ******************************
     */




    // Deleting medical examination
    /*
     ************************************
     * ********************************
     * ***********************************
     * *********************************
     * ******************************
     */

}
