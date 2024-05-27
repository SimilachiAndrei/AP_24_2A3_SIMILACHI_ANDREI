package com.Server.service;

import com.Server.model.Pacient;
import com.Server.repository.PacientRepository;
import org.springframework.stereotype.Service;

@Service
public class PacientService extends AbstractService<Pacient, Integer> {

    public PacientService(PacientRepository repository) {
        super(repository);
    }

    @Override
    public void add(Pacient entity) {
        repository.add(entity);
    }

    @Override
    public boolean update(Integer id, Pacient entity) {
        return repository.update(id, entity);
    }
}
