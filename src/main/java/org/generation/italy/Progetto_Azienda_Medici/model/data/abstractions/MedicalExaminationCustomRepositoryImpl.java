package org.generation.italy.Progetto_Azienda_Medici.model.data.abstractions;

import jakarta.persistence.*;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.MedicalExamination;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.State;
import org.springframework.transaction.annotation.Transactional;

public class MedicalExaminationCustomRepositoryImpl implements MedicalExaminationCustomRepository{

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void changeMedicalExaminationToNull(long id) {
        Query query = em.createQuery("""
            update MedicalExamination me
            set me.state = :newState
            where me.id = :id
            and me.state <> :excludeState
            """);
        query.setParameter("newState", State.ANNULLATO);
        query.setParameter("id", id);
        query.setParameter("excludeState", State.FATTO);
        int numRowsUpdated = query.executeUpdate();
        if (numRowsUpdated == 0) {
            throw new EntityNotFoundException("No MedicalExamination entity found with id: " + id);
        }
    }

    @Transactional
    @Override
    public void changeMedicalExaminationToProgrammed(long id) {
        Query query = em.createQuery("""
            update MedicalExamination me
            set me.state = :newState
            where me.id = :id
            and me.state <> :excludeState
            and me.state <> :excludeState2
            """);
        query.setParameter("newState", State.PROGRAMMATO);
        query.setParameter("id", id);
        query.setParameter("excludeState", State.FATTO);
        query.setParameter("excludeState2", State.ANNULLATO);
        int numRowsUpdated = query.executeUpdate();
        if (numRowsUpdated == 0) {
            throw new EntityNotFoundException("No MedicalExamination entity found with id: " + id);
        }
    }
}
