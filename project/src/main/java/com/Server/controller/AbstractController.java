package com.Server.controller;

import com.Server.service.GenericService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class AbstractController<T, ID> implements GenericController<T, ID> {

    protected GenericService<T, ID> service;

    public AbstractController(GenericService<T, ID> service) {
        this.service = service;
    }

    @PostMapping
    @Override
    public void add(@RequestBody T entity) {
        service.add(entity);
    }

    @GetMapping("/{id}")
    @Override
    public T get(@PathVariable ID id) {
        return service.get(id);
    }

    @GetMapping
    @Override
    public List<T> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    @Override
    public boolean delete(@PathVariable ID id) {
        return service.delete(id);
    }

    @PutMapping("/{id}")
    @Override
    public boolean update(@PathVariable ID id, @RequestBody T entity) {
        return service.update(id, entity);
    }
}
