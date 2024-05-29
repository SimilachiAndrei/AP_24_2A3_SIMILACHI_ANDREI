package com.Server.repository;

import com.Server.model.Statistics;
import jakarta.transaction.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class StatisticsRepository extends AbstractRepository<Statistics, Integer> {

    public StatisticsRepository() {
        super(Statistics.class);
    }

    @Override
    protected String getTableName() {
        return "get_drug_statistics_and_prediction()";
    }
}
