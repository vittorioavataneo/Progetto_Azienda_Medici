package org.generation.italy.Progetto_Azienda_Medici.model.services.implementations;

import org.generation.italy.Progetto_Azienda_Medici.model.data.abstractions.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class GenericService<T> {
    private GenericRepository<T> genRepo;
    @Autowired
    public GenericService(GenericRepository<T> genRepo) {
        this.genRepo = genRepo;
    }

    public Iterable<T> findAll() {
        return genRepo.findAll();
    }
    public Optional<T> findById(long id) {
        return genRepo.findById(id);
    }
    @Transactional
    public T create(T entity) {
        return genRepo.save(entity);
    }
    @Transactional
    public void update(T entity) {
        genRepo.save(entity);
    }
    @Transactional
    public void deleteById(long id) {
        genRepo.deleteById(id);
    }
}
