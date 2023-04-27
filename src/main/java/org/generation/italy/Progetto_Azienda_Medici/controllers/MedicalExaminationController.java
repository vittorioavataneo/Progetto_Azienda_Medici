package org.generation.italy.Progetto_Azienda_Medici.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.generation.italy.Progetto_Azienda_Medici.dtos.MedicalExaminationDto;
import org.generation.italy.Progetto_Azienda_Medici.model.data.abstractions.GenericRepository;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.MedicalExamination;
import org.generation.italy.Progetto_Azienda_Medici.model.services.abstractions.AbstractDidacticService;
import org.generation.italy.Progetto_Azienda_Medici.model.services.implementations.GenericService;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "api/medExamination")
public class MedicalExaminationController {

    private AbstractDidacticService didacticService;
    private GenericService<MedicalExamination> medicalExaminationService;

    @Autowired
    public MedicalExaminationController(AbstractDidacticService didacticService,
                             GenericRepository<MedicalExamination> examinationRepo) {
        this.didacticService = didacticService;
        this.medicalExaminationService = new GenericService<>(examinationRepo);
    }

    // Create new medical Examination
    @PostMapping()
    public ResponseEntity<MedicalExaminationDto> createMedicalExamination(@RequestBody MedicalExaminationDto medicalExaminationDto){
        MedicalExamination mD = medicalExaminationDto.toMedicalExamination();
        var resultMedicalExamination = medicalExaminationService.create(mD);
        return ResponseEntity.created(URI.create("api/medExamination"+mD.getId())).body(MedicalExaminationDto.fromMedicalExamination(resultMedicalExamination));
    }

    // Insert Doctor
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMedicalExamination(@RequestBody MedicalExaminationDto examinationDto,
                                             @PathVariable long id) {
        if (examinationDto.getId() != id) {
            return ResponseEntity.badRequest().build();
        }
        MedicalExamination examination = examinationDto.toMedicalExamination();
        try {
            this.medicalExaminationService.update(examination);
            return ResponseEntity.noContent().build();
        } catch (DataException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    // Delete Patient medical examination
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalExamination(@PathVariable long id){
        medicalExaminationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Find Examination by Doctor id
    @GetMapping("/doctor/{id}")
    public ResponseEntity<Iterable<MedicalExaminationDto>> findAllDoctorMedicalExamination(@PathVariable long id){
        Iterable<MedicalExamination> examinations = didacticService.findAllMedicalExaminationByDoctorId(id);
        return ResponseEntity.ok().body(MedicalExaminationDto.fromExaminationIterable(examinations));
    }

    // Find Examination by Patient id
    @GetMapping("/patient/{id}")
    public ResponseEntity<Iterable<MedicalExaminationDto>> findAllPatientMedicalExamination(@PathVariable long id){
        Iterable<MedicalExamination> examinations = didacticService.findAllMedicalExaminationByDoctorId(id);
        return ResponseEntity.ok().body(MedicalExaminationDto.fromExaminationIterable(examinations));
    }

}
