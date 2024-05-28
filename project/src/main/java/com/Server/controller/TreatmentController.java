package com.Server.controller;

import com.Server.model.Treatment;
import com.Server.service.TreatmentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/treatments")
public class TreatmentController extends AbstractController<Treatment, Integer> {

    public TreatmentController(TreatmentService service) {
        super(service);
    }
}
