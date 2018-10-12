package com.resort.springbootMolvenoLake.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller("menuguestcontroller")
@RequestMapping("/guest/menu")
public class MenuItemGuestController {
    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("title", "Menu");
        return "data/menu/guest";
    }
}
