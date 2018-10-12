package com.resort.springbootMolvenoLake.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller("menucontroller")
@RequestMapping("/menu")
public class MenuItemController {
    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("title", "Menu Item");
        return "data/menu/index";
    }
}
