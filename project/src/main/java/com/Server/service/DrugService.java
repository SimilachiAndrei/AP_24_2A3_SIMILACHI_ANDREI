package com.Server.service;

import com.Server.model.Drug;
import com.Server.repository.DrugRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugService extends AbstractService<Drug, Integer> {

    public DrugService(DrugRepository repository) {
        super(repository);
    }

    @Override
    public void add(Drug entity) {
        repository.add(entity);
    }

    @Override
    public boolean update(Integer id, Drug entity) {
        return repository.update(id, entity);
    }

    public List<Drug> getDrugsByName(String name){
        return ((DrugRepository)repository).getDrugsByName(name);
    }

}
