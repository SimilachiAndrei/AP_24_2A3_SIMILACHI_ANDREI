package com.Server.controller;

import com.Server.model.Drug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.Server.service.DrugService;

import java.util.List;

@RestController
public class DrugController {

    @Autowired
    private DrugService drugService;

//merge si asa - depinde de cum e mai comod de trimis din front
//    @PostMapping("/api/post/drugs")
//    public void addDrug(@RequestParam String name, @RequestParam int quantity) {
//        drugService.addDrug(name, quantity);
//    }

    @GetMapping("/api/get/drugs/{name}")
    public Drug getDrug(@PathVariable String name) {
        return drugService.getDrug(name);
    }

    @GetMapping("/api/get/drugs")
    public List<Drug> getDrugs() {
        return drugService.getDrugs();
    }

    @PostMapping("/api/post/drugs")
    public void addDrug(@RequestBody Drug drug) {
        drugService.addDrug(drug.getName(), drug.getQuantity());
    }

    @DeleteMapping("/api/delete/drugs/{id}")
    public boolean deleteDrug(@PathVariable Integer id) {
        return drugService.deleteDrug(id);
    }

    @PutMapping("/api/put/drugs/{id}/{quantity}")
    public boolean updateDrugQuantity(@PathVariable Integer id, @PathVariable Integer quantity) {
        return drugService.updateDrugQuantity(id,quantity);
    }
    // Add other endpoints for handling HTTP requests
}