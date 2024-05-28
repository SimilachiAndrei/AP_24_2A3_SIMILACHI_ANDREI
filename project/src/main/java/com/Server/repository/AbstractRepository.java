package com.Server.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public abstract class AbstractRepository<T, ID> implements GenericRepository<T, ID> {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    private Class<T> type;

    public AbstractRepository(Class<T> type) {
        this.type = type;
    }

    @Override
    public void add(T entity) {
        // Implement the add logic using jdbcTemplate.update
    }

    @Override
    public T get(ID id) {
        String sql = "SELECT * FROM " + getTableName() + " WHERE id = ?";
        List<T> results = jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(type));
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public List<T> getAll() {
        String sql = "SELECT * FROM " + getTableName();
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(type));
    }

    @Override
    public boolean delete(ID id) {
        String sql = "DELETE FROM " + getTableName() + " WHERE id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    @Override
    public boolean update(ID id, T entity) {
        // Implement the update logic using jdbcTemplate.update
        return false;
    }

    protected abstract String getTableName();
}
