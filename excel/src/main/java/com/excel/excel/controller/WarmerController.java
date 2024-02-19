package com.excel.excel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WarmerController {
    @GetMapping("/started")
    public String sayHello() {
        return "Project Runned Successfully";
    }
}
