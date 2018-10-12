package com.resort.springbootMolvenoLake.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    // private IngredientService service;

    @RequestMapping("/")
    public ModelAndView index() {
        Map<String, Object> model = new HashMap<>();
        //Iterable<Ingredient> ingredients = service.all();
        // model.put("hello", "World!");

        return new ModelAndView("index", model);
    }
}
