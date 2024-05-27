package com.Server.controller;

import com.Server.model.Drug;
import com.Server.service.DrugService;
import com.Server.service.PacientTreatmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/drugs")
public class DrugController extends AbstractController<Drug, Integer> {

    public DrugController(DrugService service) {
        super(service);
    }

    @GetMapping("/name/{name}")
    public List<Drug> getDrugsByName(@PathVariable String name) {
        return ((DrugService) service).getDrugsByName(name);
    }


}
