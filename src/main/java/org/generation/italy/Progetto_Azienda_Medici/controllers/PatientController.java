package org.generation.italy.Progetto_Azienda_Medici.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.generation.italy.Progetto_Azienda_Medici.dtos.*;
import org.generation.italy.Progetto_Azienda_Medici.model.data.abstractions.GenericRepository;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Patient;
import org.generation.italy.Progetto_Azienda_Medici.model.services.abstractions.AbstractDidacticService;
import org.generation.italy.Progetto_Azienda_Medici.model.services.implementations.GenericService;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "api/auth/patient")
public class PatientController {

    private AbstractDidacticService didacticService;
    private GenericService<Patient> genericServicePatient;

    @Autowired
    public PatientController(AbstractDidacticService didacticService,
                             GenericRepository<Patient> patientRepo) {
        this.didacticService = didacticService;
        this.genericServicePatient = new GenericService<>(patientRepo);
    }

    // Creation Patient
    @PostMapping()
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto){

        Patient patient = patientDto.toPatient();
        var resultPatient = genericServicePatient.create(patient);
        return ResponseEntity.created(URI.create("api/auth/patient"+patient.getId())).body(PatientDto.fromPatient(resultPatient));
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
            genericServicePatient.update(patient);
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
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePatientById(@PathVariable long id){
        genericServicePatient.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Find all Patients
    @GetMapping()
    public ResponseEntity<Iterable<PatientDto>> findAllPatients(){

        Iterable<Patient> patientIterable = genericServicePatient.findAll();
        return ResponseEntity.ok().body(PatientDto.fromPatientIterable(patientIterable));
    }

    @GetMapping("/find/patient/{id}")
    public ResponseEntity<PatientDto> findPatientById(@PathVariable long id){
        Optional<Patient> op = genericServicePatient.findById(id);
        if (op.isEmpty()){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(PatientDto.fromPatient(op.get()));
    }
    @GetMapping("/find/{part}")
    public ResponseEntity<Iterable<PatientDto>> findPatientByName(@PathVariable String part){
        Iterable<Patient> pat = didacticService.findPatientByName(part);
        return ResponseEntity.ok().body(PatientDto.fromPatientIterable(pat));
    }

    //find patient by name
    @GetMapping("/find/patient/name/{part}")
    public ResponseEntity<Iterable<PatientDto>> findPatientByName(@PathVariable String part){
        Iterable<Patient> pat = didacticService.findPatientByName(part);
        return ResponseEntity.ok().body(PatientDto.fromPatientIterable(pat));
    }

    // Find all Patients of doctor
    @GetMapping("find/patientOfDoctor/{id}")
    public ResponseEntity<Iterable<PatientDto>> findAllPatientsByDoctorId(@PathVariable long id){

        Iterable<Patient> patientIterable = didacticService.findAllPatientByDoctorId(id);
        return ResponseEntity.ok().body(PatientDto.fromPatientIterable(patientIterable));
    }

}
