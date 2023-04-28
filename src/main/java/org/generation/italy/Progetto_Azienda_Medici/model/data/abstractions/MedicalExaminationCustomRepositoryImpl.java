package org.generation.italy.Progetto_Azienda_Medici.model.data.abstractions;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.MedicalExamination;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.State;
import org.springframework.transaction.annotation.Transactional;

public class MedicalExaminationCustomRepositoryImpl implements MedicalExaminationCustomRepository{

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void changeMedicalExaminationToNull(long id) {
        TypedQuery<MedicalExamination> query = em.createQuery("""
            update MedicalExamination me
            set me.state = :newState
            where me.id = :id
            and me.state <> :excludeState
            """, MedicalExamination.class);
        query.setParameter("newState", State.ANNULLATO);
        query.setParameter("id", id);
        query.setParameter("excludeState", State.FATTO);
        int numRowsUpdated = query.executeUpdate();
        if (numRowsUpdated == 0) {
            throw new EntityNotFoundException("No MedicalExamination entity found with id: " + id);
        }
    }
}
