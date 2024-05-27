package com.Server.controller;

import com.Server.model.Drug;
import com.Server.service.DrugService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/drugs")
public class DrugController extends AbstractController<Drug, Integer> {

    public DrugController(DrugService service) {
        super(service);
    }
}
