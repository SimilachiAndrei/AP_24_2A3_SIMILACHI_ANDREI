package com.Server.controller;

import com.Server.model.DrugStatistics;
import com.Server.service.DrugStatisticsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/drugstatistics")
public class DrugStatisticsController extends AbstractController<DrugStatistics, Integer> {

    public DrugStatisticsController(DrugStatisticsService service) {
        super(service);
    }
}
