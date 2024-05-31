package com.Server.repository;

import com.Server.model.History;
import jakarta.transaction.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class HistoryRepository extends AbstractRepository<History, Integer> {

    public HistoryRepository() {
        super(History.class);
    }

    @Override
    protected String getTableName() {
        return "history";
    }

    @Override
    public void add(History entity) {
        String sql = "INSERT INTO history (drug_id,data, quantity,transaction) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, entity.getDrug_id(), entity.getData(), entity.getQuantity(),
                entity.getTransaction());
    }

    @Override
    public boolean update(Integer id, History entity) {
        String sql = "UPDATE history SET quantity = ? WHERE id = ?";
        return jdbcTemplate.update(sql, entity.getQuantity(), id) > 0;
    }
}
