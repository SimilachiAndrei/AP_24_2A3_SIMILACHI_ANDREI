package com.Server.service;

import com.Server.model.Treatment;
import com.Server.repository.TreatmentRepository;
import org.springframework.stereotype.Service;

@Service
public class TreatmentService extends AbstractService<Treatment, Integer> {

    public TreatmentService(TreatmentRepository repository) {
        super(repository);
    }

    @Override
    public void add(Treatment entity) {
        repository.add(entity);
    }

    @Override
    public boolean update(Integer id, Treatment entity) {
        return repository.update(id, entity);
    }
}
