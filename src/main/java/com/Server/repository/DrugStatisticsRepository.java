package com.Server.repository;

import com.Server.model.DrugStatistics;
import jakarta.transaction.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class DrugStatisticsRepository extends AbstractRepository<DrugStatistics, Integer> {

    public DrugStatisticsRepository() {
        super(DrugStatistics.class);
    }

    @Override
    protected String getTableName() {
        return "calculate_drug_statistics()";
    }
}
