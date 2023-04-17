package org.generation.italy.Progetto_Azienda_Medici.controllers;

import org.generation.italy.Progetto_Azienda_Medici.model.data.abstractions.DoctorRepository;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Doctor;
import org.generation.italy.Progetto_Azienda_Medici.model.services.abstractions.AbstractDidacticService;
import org.generation.italy.Progetto_Azienda_Medici.model.services.implementations.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class DoctorController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    private AbstractDidacticService didacticService;
    private GenericService<Doctor> genericService;
    @Autowired
    public DoctorController(AbstractDidacticService didacticService, GenericService<Doctor> genericService){
        this.didacticService=didacticService;
        this.genericService=genericService;
    }

    public void saveUser(Doctor doctor) {
        String encodedPassword = passwordEncoder.encode(doctor.getPassword());
        doctor.setPassword(encodedPassword);
        genericService.create(doctor);
    }
}
