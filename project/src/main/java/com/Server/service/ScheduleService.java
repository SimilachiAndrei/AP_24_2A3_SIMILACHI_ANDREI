package com.Server.service;

import com.Server.model.Schedule;
import com.Server.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService extends AbstractService<Schedule, Integer> {

    public ScheduleService(ScheduleRepository repository) {
        super(repository);
    }

    @Override
    public void add(Schedule entity) {
        repository.add(entity);
    }

    @Override
    public boolean update(Integer id, Schedule entity) {
        return repository.update(id, entity);
    }
}
