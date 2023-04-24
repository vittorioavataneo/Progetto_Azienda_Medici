package org.generation.italy.Progetto_Azienda_Medici.controllers;

import org.generation.italy.Progetto_Azienda_Medici.dtos.MedicalExaminationDto;
import org.generation.italy.Progetto_Azienda_Medici.model.data.abstractions.GenericRepository;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.MedicalExamination;
import org.generation.italy.Progetto_Azienda_Medici.model.services.abstractions.AbstractDidacticService;
import org.generation.italy.Progetto_Azienda_Medici.model.services.implementations.GenericService;
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

    // Delete Patient medical examination
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalExamination(@PathVariable long id){
        medicalExaminationService.deleteById(id);
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
