package com.Server.controller;

import com.Server.model.Statistics;
import com.Server.service.StatisticsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController extends AbstractController<Statistics, Integer> {

    public StatisticsController(StatisticsService service) {
        super(service);
    }
}
