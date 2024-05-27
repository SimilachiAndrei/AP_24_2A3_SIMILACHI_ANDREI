package com.Server.controller;

import com.Server.model.Schedule;
import com.Server.service.ScheduleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController extends AbstractController<Schedule, Integer> {

    public ScheduleController(ScheduleService service) {
        super(service);
    }
}
