package com.Server.service;

import com.Server.model.DrugStatistics;
import com.Server.repository.DrugStatisticsRepository;
import org.springframework.stereotype.Service;

@Service
public class DrugStatisticsService extends AbstractService<DrugStatistics, Integer> {

    public DrugStatisticsService(DrugStatisticsRepository repository) {
        super(repository);
    }
}
