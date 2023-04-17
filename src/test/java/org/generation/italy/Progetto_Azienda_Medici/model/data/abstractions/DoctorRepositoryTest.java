package org.generation.italy.Progetto_Azienda_Medici.model.data.abstractions;

import org.generation.italy.Progetto_Azienda_Medici.model.entities.Address;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Doctor;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Sex;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Specialization;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DoctorRepositoryTest {

    @Autowired
    private TestEntityManager em;
    @Autowired
    private DoctorRepository docRepo;

    private Doctor d1;
    private Doctor d2;
    private Doctor d3;
    private Address a1;
    private Specialization s1;
    private Specialization s2;

    @BeforeEach
    void setUp() {
        a1 = new Address(0, "Via le mani dal culo 5", "10133",
                "Milano", "MI", "Italy");
        s1 = new Specialization(0, "Psicologo");
        s2 = new Specialization(0, "Urologo");
        d1 = new Doctor(0, "Pippo", "Baudo", Sex.UOMO, "3405296844",
                "pippobaudo@gmail.com", "pippobaudo", "pippobaudo80",
                a1, s1, true );
        d2 = new Doctor(0, "Minnie", "Baudo", Sex.DONNA, "3463496844",
                "minniebaudo@gmail.com", "minniebaudo", "minniebaudo80",
                a1, s1, true );
        d3 = new Doctor(0, "Pluto", "Plutonio", Sex.UOMO, "3416996844",
                "pluto@gmail.com", "plutoplutonio", "plutoplutonio80",
                a1, s2, true );

        em.persist(a1);
        em.persist(s1);
        em.persist(s2);
        em.persist(d1);
        em.persist(d2);
        em.persist(d3);

    }

    @AfterEach
    void tearDown() {
        em.remove(d1);
        em.remove(d2);
        em.remove(d3);
        em.remove(a1);
        em.remove(s1);
        em.remove(s2);
    }

    @Test
    void findByName() {
        Iterable<Doctor> iterator = docRepo.findByName("Pippo");
        List<Doctor> result = new ArrayList<>();
        iterator.forEach(result::add);
        assertEquals(1, result.size());
        assertEquals("Pippo", result.get(0).getFirstname());

    }

    @Test
    void findBySpecialization() {
    }
}