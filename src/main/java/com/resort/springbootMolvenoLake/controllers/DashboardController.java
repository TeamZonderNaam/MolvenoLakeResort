package com.resort.springbootMolvenoLake.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller("dashboardcontroller")
@RequestMapping("/dashboard")
public class DashboardController {
    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("title", "Dashboard");
        return "data/dashboard/index";
    }
}