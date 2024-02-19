package com.excel.excel.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excel.excel.service.OrderService;

@RestController
@RequestMapping("excel")
public class OrderController {

    @Autowired
    OrderService orderService;
    
    @PostMapping("/process-data")
    public ResponseEntity<?> processExcelFile(@RequestParam(name ="filepath") String filePath) {
        try {
            String response = orderService.readExcelFile(filePath);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(500).body("Error processing Excel file");
        }
    }
}
