package com.Server.controller;

import com.Server.model.History;
import com.Server.service.HistoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/history")
public class HistoryController extends AbstractController<History, Integer> {

    public HistoryController(HistoryService service) {
        super(service);
    }
}
