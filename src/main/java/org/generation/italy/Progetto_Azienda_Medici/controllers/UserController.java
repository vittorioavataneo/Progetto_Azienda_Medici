package org.generation.italy.Progetto_Azienda_Medici.controllers;

import org.generation.italy.Progetto_Azienda_Medici.dtos.PatientDto;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Patient;
import org.generation.italy.Progetto_Azienda_Medici.model.services.abstractions.AbstractDidacticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "api/auth/user")
public class UserController {

    private AbstractDidacticService didacticService;

    @Autowired
    public UserController(AbstractDidacticService didacticService) {
        this.didacticService = didacticService;
    }

    @GetMapping("/find/{email}")
    public ResponseEntity<Long> findPersonIdByUserEmail(@PathVariable String email){
        Optional<Long> op = didacticService.findPersonIdByUserEmail(email);
        if (op.isEmpty()){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(op.get());
    }
}
