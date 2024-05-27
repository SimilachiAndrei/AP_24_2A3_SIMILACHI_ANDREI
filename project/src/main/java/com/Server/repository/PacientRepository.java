package com.Server.repository;

import com.Server.model.Pacient;
import org.springframework.stereotype.Repository;

@Repository
public class PacientRepository extends AbstractRepository<Pacient, Integer> {

    public PacientRepository() {
        super(Pacient.class);
    }

    @Override
    protected String getTableName() {
        return "pacients";
    }

    @Override
    public void add(Pacient entity) {
        String sql = "INSERT INTO pacients (surname, firstname) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, entity.getFirstname(), entity.getFirstname());
    }

    @Override
    public boolean update(Integer id, Pacient entity) {
        String sql = "UPDATE pacients SET name = ? , surname = ? WHERE id = ?";
        return jdbcTemplate.update(sql, entity.getSurname(), entity.getFirstname(), id) > 0;
    }
}
