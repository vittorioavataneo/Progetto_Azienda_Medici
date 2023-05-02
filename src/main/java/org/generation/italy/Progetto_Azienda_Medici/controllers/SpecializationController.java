package org.generation.italy.Progetto_Azienda_Medici.controllers;

import org.generation.italy.Progetto_Azienda_Medici.dtos.SpecializationDto;
import org.generation.italy.Progetto_Azienda_Medici.model.data.abstractions.GenericRepository;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Patient;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Specialization;
import org.generation.italy.Progetto_Azienda_Medici.model.services.abstractions.AbstractDidacticService;
import org.generation.italy.Progetto_Azienda_Medici.model.services.implementations.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "api/auth/specialization")
public class SpecializationController {

    private AbstractDidacticService didacticService;
    private GenericService<Patient> genericServicePatient;

    @Autowired
    public SpecializationController(AbstractDidacticService didacticService,
                             GenericRepository<Patient> patientRepo) {
        this.didacticService = didacticService;
        this.genericServicePatient = new GenericService<>(patientRepo);
    }

    @GetMapping("/find/{specializationName}")
    public ResponseEntity<SpecializationDto> findSpecializationByName(@PathVariable String specializationName){
        Optional<Specialization> os = didacticService.findSpecializationBySpecializationName(specializationName);
        if (os.isEmpty()){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(SpecializationDto.fromSpecialization(os.get()));
    }
}
