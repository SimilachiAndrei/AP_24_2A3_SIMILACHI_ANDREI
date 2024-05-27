package com.Server.service;

import com.Server.model.History;
import com.Server.repository.HistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class HistoryService extends AbstractService<History, Integer> {

    public HistoryService(HistoryRepository repository) {
        super(repository);
    }

    @Override
    public void add(History entity) {
        repository.add(entity);
    }

    @Override
    public boolean update(Integer id, History entity) {
        return repository.update(id, entity);
    }
}
