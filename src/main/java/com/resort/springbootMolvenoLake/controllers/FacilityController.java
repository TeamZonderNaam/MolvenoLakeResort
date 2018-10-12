package com.resort.springbootMolvenoLake.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.resort.springbootMolvenoLake.models.Facility;
import com.resort.springbootMolvenoLake.repositories.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

    @Controller("facilitycontroller")
    @RequestMapping("/facility")
    public class FacilityController {
        @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("title", "Dashboard");
        return "data/facility/index";
    }
}