package org.generation.italy.Progetto_Azienda_Medici.model.data.abstractions;

import org.generation.italy.Progetto_Azienda_Medici.model.entities.Specialization;

import java.util.Optional;

public interface SpecializationRepository extends GenericRepository<Specialization>{

    Optional<Specialization> findBySpecializationName(String specializationName);
}
