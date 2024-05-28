package com.Server.controller;

import com.Server.model.Pacient;
import com.Server.service.PacientService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pacients")
public class PacientController extends AbstractController<Pacient, Integer> {

    public PacientController(PacientService service) {
        super(service);
    }
}
