package com.example.demo.controller;

import com.example.demo.model.Game;
import com.example.demo.service.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    GameServiceImpl service;

    @RequestMapping("/init")
    public Game initGame(){
        return service.init();
    }
}
