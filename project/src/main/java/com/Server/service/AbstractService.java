package com.Server.service;

import com.Server.repository.GenericRepository;
import java.util.List;

public abstract class AbstractService<T, ID> implements GenericService<T, ID> {

    protected GenericRepository<T, ID> repository;

    public AbstractService(GenericRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public void add(T entity) {
        repository.add(entity);
    }

    @Override
    public T get(ID id) {
        return repository.get(id);
    }

    @Override
    public List<T> getAll() {
        return repository.getAll();
    }

    @Override
    public boolean delete(ID id) {
        return repository.delete(id);
    }

    @Override
    public boolean update(ID id, T entity) {
        return repository.update(id, entity);
    }
}
