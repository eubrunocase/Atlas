package com.example.Atlas.service;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public abstract class BaseService<T> {

    protected final JpaRepository<T, Long> repository;

    public BaseService (JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public T update(Long id, T entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
