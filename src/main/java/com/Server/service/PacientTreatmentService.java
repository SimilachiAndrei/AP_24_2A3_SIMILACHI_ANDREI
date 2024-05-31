package com.Server.service;

import com.Server.model.PacientTreatment;
import com.Server.repository.PacientTreatmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacientTreatmentService extends AbstractService<PacientTreatment,Integer> {

    private final PacientTreatmentRepository pacientTreatmentRepository;

    public PacientTreatmentService(PacientTreatmentRepository pacientTreatmentRepository) {
        super(pacientTreatmentRepository);
        this.pacientTreatmentRepository = pacientTreatmentRepository;
    }

}
