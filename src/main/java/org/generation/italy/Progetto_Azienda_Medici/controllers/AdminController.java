package org.generation.italy.Progetto_Azienda_Medici.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.generation.italy.Progetto_Azienda_Medici.dtos.AdminDto;

import org.generation.italy.Progetto_Azienda_Medici.model.data.abstractions.GenericRepository;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Admin;
import org.generation.italy.Progetto_Azienda_Medici.model.services.abstractions.AbstractDidacticService;
import org.generation.italy.Progetto_Azienda_Medici.model.services.implementations.GenericService;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@CrossOrigin
@RestController
@RequestMapping (value = "api/auth/admin")
public class AdminController {

    private AbstractDidacticService didacticService;
    private GenericService<Admin> genericServiceAdmin;

    @Autowired
    public AdminController(AbstractDidacticService didacticService, GenericRepository<Admin> adminRepo) {

        this.didacticService = didacticService;
        this.genericServiceAdmin = new GenericService<>(adminRepo);
    }

    //Creation Admin
    @PostMapping()
    public ResponseEntity<AdminDto> createAdmin(@RequestBody AdminDto adminDto){

        Admin admin = adminDto.toAdmin();
        var resultAdmin = genericServiceAdmin.create(admin);
        return ResponseEntity.created(URI.create("api/auth/admin"+admin.getId())).body(AdminDto.fromAdmin(resultAdmin));
    }

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

    // Delete Admin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdminById(@PathVariable long id){
        genericServiceAdmin.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Find all admins
    @GetMapping()
    public ResponseEntity<Iterable<AdminDto>> findAllAdmins(){

        Iterable<Admin> admidIterable = genericServiceAdmin.findAll();
        return ResponseEntity.ok().body(AdminDto.fromAdminIterable(admidIterable));
    }

}
