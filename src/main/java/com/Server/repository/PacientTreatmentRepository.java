package com.Server.repository;

import com.Server.model.PacientTreatment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PacientTreatmentRepository extends AbstractRepository<PacientTreatment, Integer> {

    public PacientTreatmentRepository() {
        super(PacientTreatment.class);
    }

    @Override
    protected String getTableName() {
        return "pacient_treatment";
    }

    @Override
    public void add(PacientTreatment entity) {
        String sql = "INSERT INTO pacient_treatment (pacient_id, treatment_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, entity.getPacientId(), entity.getTreatmentId());
    }
    @Override
    public PacientTreatment get(Integer pacientId) {
        String sql = "SELECT pt.id, p.surname, p.firstname, pt.pacient_id AS pacientId, t.id AS treatmentId, " +
                "t.illness, t.drug_id AS drugId, d.name AS drugName, t.dailyquantity " +
                "FROM pacients p " +
                "JOIN pacient_treatment pt ON p.id = pt.pacient_id " +
                "JOIN treatments t ON pt.treatment_id = t.id " +
                "JOIN drugs d ON d.id = t.drug_id " +
                "WHERE p.id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{pacientId}, new BeanPropertyRowMapper<>(PacientTreatment.class));
    }

    @Override
    public List<PacientTreatment> getAll() {
        String sql = "SELECT pt.id, p.surname, p.firstname, pt.pacient_id AS pacientId, t.id AS treatmentId, " +
                "t.illness, t.drug_id AS drugId, d.name AS drugName, t.dailyquantity " +
                "FROM pacients p " +
                "JOIN pacient_treatment pt ON p.id = pt.pacient_id " +
                "JOIN treatments t ON pt.treatment_id = t.id " +
                "JOIN drugs d ON d.id = t.drug_id ";
        return jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<>(PacientTreatment.class));
    }
}
