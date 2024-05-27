package com.Server.repository;

import com.Server.model.Treatment;
import org.springframework.stereotype.Repository;

@Repository
public class TreatmentRepository extends AbstractRepository<Treatment, Integer> {

    public TreatmentRepository() {
        super(Treatment.class);
    }

    @Override
    protected String getTableName() {
        return "treatments";
    }

    @Override
    public void add(Treatment entity) {
        String sql = "INSERT INTO treatments (illness, drug_id,dailyquantity) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, entity.getIllness(), entity.getDrug_id(), entity.getDailyquantity());
    }

    @Override
    public boolean update(Integer id, Treatment entity) {
        String sql = "UPDATE treatments SET dailyquantity = ? WHERE id = ?";
        return jdbcTemplate.update(sql, entity.getDailyquantity(), id) > 0;
    }
}
