package com.Server.service;

import com.Server.model.Drug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Server.repository.DrugRepository;

import java.util.List;

@Service
public class DrugService {

    @Autowired
    private DrugRepository drugRepository;

    public void addDrug(String name, int quantity) {
        drugRepository.addDrug(name, quantity);
    }

    public Drug getDrug(String name)
    {
        return drugRepository.getDrug(name);
    }

    public List<Drug> getDrugs() {
        return drugRepository.getDrugs();
    }

    public boolean deleteDrug(Integer id) {
        return drugRepository.deleteDrug(id);
    }

    public boolean updateDrugQuantity(Integer id, Integer quantity) {
        return drugRepository.updateDrugQuantity(id,quantity);
    }
    // Add other methods for business logic
}