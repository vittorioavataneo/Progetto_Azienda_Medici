package org.generation.italy.Progetto_Azienda_Medici.model.data.abstractions;

import org.springframework.stereotype.Repository;

@Repository
public interface MedicalExaminationCustomRepository {

    void changeMedicalExaminationToNull(long id);

    void changeMedicalExaminationToProgrammed(long id);
}
