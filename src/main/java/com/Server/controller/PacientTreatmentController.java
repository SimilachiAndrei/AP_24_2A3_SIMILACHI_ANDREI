package com.Server.controller;

import com.Server.model.PacientTreatment;
import com.Server.service.PacientTreatmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pacient-treatments")
public class PacientTreatmentController extends AbstractController<PacientTreatment,Integer>{

    private final PacientTreatmentService pacientTreatmentService;

    public PacientTreatmentController(PacientTreatmentService pacientTreatmentService) {
        super(pacientTreatmentService);
        this.pacientTreatmentService = pacientTreatmentService;
    }

}
