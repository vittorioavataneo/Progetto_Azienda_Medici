package org.generation.italy.Progetto_Azienda_Medici.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Specialization;

@Getter
@Setter
@NoArgsConstructor
public class SpecializationDto {

    private long id;
    private String specializationName;

    public SpecializationDto(long id, String specializationName) {
        this.id = id;
        this.specializationName = specializationName;
    }

    public static SpecializationDto fromSpecialization(Specialization specialization) {
        return new SpecializationDto(
                specialization.getId(),
                specialization.getSpecializationName()
        );
    }

    public Specialization toSpecialization() {
        return new Specialization(
                id,
                specializationName
        );
    }
}
