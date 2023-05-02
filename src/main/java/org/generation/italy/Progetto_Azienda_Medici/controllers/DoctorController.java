package org.generation.italy.Progetto_Azienda_Medici.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.generation.italy.Progetto_Azienda_Medici.dtos.DoctorDto;
import org.generation.italy.Progetto_Azienda_Medici.dtos.PatientDto;
import org.generation.italy.Progetto_Azienda_Medici.dtos.SimpleDoctorDto;
import org.generation.italy.Progetto_Azienda_Medici.model.data.abstractions.GenericRepository;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Doctor;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Patient;
import org.generation.italy.Progetto_Azienda_Medici.model.services.abstractions.AbstractDidacticService;
import org.generation.italy.Progetto_Azienda_Medici.model.services.implementations.GenericService;
import org.generation.italy.Progetto_Azienda_Medici.model.services.implementations.StandardDidacticService;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "api/auth/doctor")
public class DoctorController {


    private AbstractDidacticService didacticService;
    private GenericService<Doctor> genericServiceDoctor;

    @Autowired
    public DoctorController(AbstractDidacticService didacticService,
                            GenericRepository<Doctor> doctorRepo) {
        this.didacticService = didacticService;
        this.genericServiceDoctor = new GenericService<>(doctorRepo);
    }

    // Creation Doctor
    @PostMapping()
    public ResponseEntity<DoctorDto> createDoctor(@RequestBody DoctorDto doctorDto){

        Doctor doctor = doctorDto.toDoctor();
        var resultDoctor = genericServiceDoctor.create(doctor);
        return  ResponseEntity.created(URI.create("api/auth/doctor"+doctor.getId())).body(DoctorDto.fromDoctor(resultDoctor));
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

    // Delete Doctor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctorById(@PathVariable long id){
        genericServiceDoctor.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<DoctorDto> findDoctorById(@PathVariable long id){
        Optional<Doctor> op = genericServiceDoctor.findById(id);
        if (op.isEmpty()){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(DoctorDto.fromDoctor(op.get()));
    }

    // Find all doctors
    @GetMapping()
    public ResponseEntity<Iterable<DoctorDto>> findAllDoctors(){

        Iterable<Doctor> doctorIterable = genericServiceDoctor.findAll();
        return ResponseEntity.ok().body(DoctorDto.fromDoctorIterable(doctorIterable));
    }

    // Find Doctor by specialization
    @GetMapping("/find/specializationDoctor/{part}")
    public ResponseEntity<Iterable<DoctorDto>> findDoctorBySpecialization(@PathVariable String part){
        Iterable<Doctor> doctor = didacticService.findDoctorBySpecialization(part);
        return ResponseEntity.ok().body(DoctorDto.fromDoctorIterable(doctor));
    }

    //Find Doctor by name
    @GetMapping("/find/doctor/{part}")
    public ResponseEntity<Iterable<SimpleDoctorDto>> findDoctorByName(@PathVariable String part){
        Iterable<Doctor> doc = didacticService.findDoctorByName(part);
        return  ResponseEntity.ok().body(SimpleDoctorDto.fromSimpleDoctorIterable(doc));
    }
}



















