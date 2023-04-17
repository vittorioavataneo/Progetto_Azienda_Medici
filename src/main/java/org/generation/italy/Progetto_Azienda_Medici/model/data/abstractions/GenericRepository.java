package org.generation.italy.Progetto_Azienda_Medici.model.data.abstractions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<T> extends JpaRepository<T, Long> {
}
