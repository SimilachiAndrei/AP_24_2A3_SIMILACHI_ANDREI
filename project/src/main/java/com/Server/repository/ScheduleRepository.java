package com.Server.repository;

import com.Server.model.Schedule;
import jakarta.transaction.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleRepository extends AbstractRepository<Schedule, Integer> {

    public ScheduleRepository() {
        super(Schedule.class);
    }

    @Override
    protected String getTableName() {
        return "schedule";
    }

    @Override
    public void add(Schedule entity) {
        String sql = "INSERT INTO schedule (drug_id,data, quantity) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, entity.getDrug_id(), entity.getData(), entity.getQuantity());
    }

    @Override
    public boolean update(Integer id, Schedule entity) {
        String sql = "UPDATE schedule SET quantity = ? WHERE id = ?";
        return jdbcTemplate.update(sql, entity.getQuantity(), id) > 0;
    }
}
