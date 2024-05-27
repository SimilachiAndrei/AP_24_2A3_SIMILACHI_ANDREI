package com.Server.repository;

import com.Server.model.Drug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DrugRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addDrug(String name, int quantity) {
        String sql = "INSERT INTO drugs (name, quantity) VALUES (?, ?)";
        jdbcTemplate.update(sql, name, quantity);
    }

    public Drug getDrug(String name) {
        String sql = "SELECT * FROM drugs WHERE name = ?";
        List<Drug> drugs = jdbcTemplate.query(sql, new Object[]{name}, new BeanPropertyRowMapper<>(Drug.class));
        return drugs.isEmpty() ? null : drugs.get(0);
    }

    public List<Drug> getDrugs() {
        String sql = "select * from drugs;";
        List<Drug> drugs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Drug.class));
        return drugs;
    }

    public boolean deleteDrug(Integer id) {
        String sql = "DELETE FROM drugs WHERE id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    public boolean updateDrugQuantity(Integer id, Integer quantity) {
        String sql = "update drugs set quantity = ? WHERE id = ?";
        return jdbcTemplate.update(sql, quantity,id) > 0;
    }
    // Add other methods for querying and updating the database
}