package org.generation.italy.Progetto_Azienda_Medici.controllers;

import org.generation.italy.Progetto_Azienda_Medici.model.entities.Doctor;
import org.generation.italy.Progetto_Azienda_Medici.model.services.abstractions.AbstractDidacticService;
import org.generation.italy.Progetto_Azienda_Medici.model.services.implementations.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;

public class DoctorController {

    private BCryptPasswordEncoder passwordEncoder;
    private AbstractDidacticService didacticService;
    private GenericService<Doctor> genericService;
    @Autowired
    public DoctorController(BCryptPasswordEncoder passwordEncoder, AbstractDidacticService didacticService,
                            GenericService<Doctor> genericService){
        this.passwordEncoder = passwordEncoder;
        this.didacticService=didacticService;
        this.genericService=genericService;
    }

    @PostMapping("/sign_up")
    public void saveUser(Doctor doctor) {
        String encodedPassword = passwordEncoder.encode(doctor.getPassword());
        doctor.setPassword(encodedPassword);
        genericService.create(doctor);
    }
}
