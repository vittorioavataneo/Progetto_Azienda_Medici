package org.generation.italy.Progetto_Azienda_Medici.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.generation.italy.Progetto_Azienda_Medici.dtos.AdminDto;
import org.generation.italy.Progetto_Azienda_Medici.dtos.DoctorDto;
import org.generation.italy.Progetto_Azienda_Medici.dtos.PatientDto;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Admin;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Doctor;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Patient;
import org.generation.italy.Progetto_Azienda_Medici.model.services.abstractions.AbstractDidacticService;
import org.generation.italy.Progetto_Azienda_Medici.model.services.implementations.GenericService;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.converter.RsaKeyConverters;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import javax.swing.*;
import java.net.URI;

@RestController
@RequestMapping (value = "personalArea/admin")
public class AdminController {

    private AbstractDidacticService didacticService;
    private GenericService<Doctor> genericServiceDoctor;
    private GenericService<Patient> genericServicePatient;
    private GenericService<Admin> genericServiceAdmin;


    @Autowired
    public AdminController(AbstractDidacticService didacticService, GenericService<Doctor> genericServiceDoctor, GenericService<Patient> genericServicePatient, GenericService<Admin> genericServiceAdmin) {

        this.didacticService = didacticService;
        this.genericServiceDoctor = genericServiceDoctor;
        this.genericServicePatient = genericServicePatient;
        this.genericServiceAdmin = genericServiceAdmin;
    }

    //CREATION ADMIN DOCTOR & PATIENT

    //Creation Admin
    @PostMapping()
    public ResponseEntity<AdminDto> createAdmin(@RequestBody AdminDto adminDto){

        Admin admin = adminDto.toAdmin();
        var resultAdmin = this.genericServiceAdmin.create(admin);
        return ResponseEntity.created(URI.create("personalArea/admin"+admin.getId())).body(AdminDto.fromAdmin(resultAdmin));
    }

    // Creation Doctor
    @PostMapping()
    public ResponseEntity<DoctorDto> createDoctor(@RequestBody DoctorDto doctorDto){

        Doctor doctor = doctorDto.toDoctor();
        var resultDoctor = this.genericServiceDoctor.create(doctor);
        return  ResponseEntity.created(URI.create("personalArea/admin"+doctor.getId())).body(DoctorDto.fromDoctor(resultDoctor));
    }

    // Creation Patient
    @PostMapping()
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto){

        Patient patient = patientDto.toPatient();
        var resultPatient = this.genericServicePatient.create(patient);
        return ResponseEntity.created(URI.create("personalArea/admin"+patient.getId())).body(PatientDto.fromPatient(resultPatient));
    }



    // INSERT ADMIN DOCTOR & PATIENT

    // Insert Admin
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAdmin(@RequestBody AdminDto adminDto,
                                            @PathVariable long id){
        if (adminDto.getId() != id){
            return ResponseEntity.badRequest().build();
        }
        Admin admin = adminDto.toAdmin();
        try {
            this.genericServiceAdmin.update(admin);
            return ResponseEntity.noContent().build();
        } catch (DataException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        } catch (EntityNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    // Insert Doctor
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDoctor(@RequestBody DoctorDto doctorDto,
                                             @PathVariable long id){
        if (doctorDto.getId() != id){
            return ResponseEntity.badRequest().build();
        }
        Doctor doctor = doctorDto.toDoctor();
        try {
            this.genericServiceDoctor.update(doctor);
            return ResponseEntity.noContent().build();
        } catch (DataException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        } catch (EntityNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
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

    // DELETE ADMIN DOCTOR PATIENT

    // Delete Admin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdminById(@PathVariable long id){
        this.genericServiceAdmin.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Delete Doctor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctorById(@PathVariable long id){
        this.genericServiceDoctor.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Delete Patient
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatientById(@PathVariable long id){
        this.genericServicePatient.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    // FIND ALL ADMIN DOCTOR & PATIENT

    // Find all admins
    @GetMapping()
    public ResponseEntity<Iterable<AdminDto>> findAllAdmins(){

        Iterable<Admin> admidIterable = this.genericServiceAdmin.findAll();
        return ResponseEntity.ok().body(AdminDto.fromAdminIterable(admidIterable));
    }

    // Find all doctors
    @GetMapping()
    public ResponseEntity<Iterable<DoctorDto>> findAllDoctors(){

        Iterable<Doctor> doctorIterable = this.genericServiceDoctor.findAll();
        return ResponseEntity.ok().body(DoctorDto.fromDoctorIterable(doctorIterable));
    }

    // Find all Patients
    @GetMapping()
    public ResponseEntity<Iterable<PatientDto>> findAllPatients(){

        Iterable<Patient> patientIterable = this.genericServicePatient.findAll();
        return ResponseEntity.ok().body(PatientDto.fromPatientIterable(patientIterable));
    }



}
