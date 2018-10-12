package com.resort.springbootMolvenoLake.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.resort.springbootMolvenoLake.models.Room;
import com.resort.springbootMolvenoLake.repositories.RoomsRepository;


import java.util.Map;

@Controller("roomcontroller")
@RequestMapping("/room")
public class RoomController {

        @RequestMapping("/")
        public String index(Map<String, Object> model) {
            model.put("title", "Dashboard");
            return "data/room/index";
        }
    }