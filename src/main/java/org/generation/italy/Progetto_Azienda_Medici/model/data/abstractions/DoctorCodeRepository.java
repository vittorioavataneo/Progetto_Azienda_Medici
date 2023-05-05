package org.generation.italy.Progetto_Azienda_Medici.model.data.abstractions;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.Doctor;
import org.generation.italy.Progetto_Azienda_Medici.model.entities.DoctorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

public interface DoctorCodeRepository extends GenericRepository<DoctorCode> {

    @Query("""
    select case when count(dc) > 0 then true else false end
    from DoctorCode dc
    where dc.codice = :part
    """)
    boolean verifyCode(String part);
}
