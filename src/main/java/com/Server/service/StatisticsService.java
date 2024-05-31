package com.Server.service;

import com.Server.model.Statistics;
import com.Server.repository.StatisticsRepository;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService extends AbstractService<Statistics, Integer> {

    public StatisticsService(StatisticsRepository repository) {
        super(repository);
    }
}
