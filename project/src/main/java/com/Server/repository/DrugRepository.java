package com.Server.repository;

import com.Server.model.Drug;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DrugRepository extends AbstractRepository<Drug, Integer> {

    public DrugRepository() {
        super(Drug.class);
    }

    @Override
    protected String getTableName() {
        return "drugs";
    }

    @Override
    public void add(Drug entity) {
        String sql = "INSERT INTO drugs (name, quantity) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, entity.getName(), entity.getQuantity());
    }

    public List<Drug> getDrugsByName(String name) {
        String sql = "SELECT * FROM drugs where name = ?";
        return jdbcTemplate.query(sql, new Object[]{name}, new BeanPropertyRowMapper<>(Drug.class));
    }

    @Override
    public boolean update(Integer id, Drug entity) {
        String sql = "UPDATE drugs SET quantity = ? WHERE id = ?";
        return jdbcTemplate.update(sql, entity.getQuantity(), id) > 0;
    }
}
