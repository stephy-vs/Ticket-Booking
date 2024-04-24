package com.TicketBooking.controller;

import com.TicketBooking.model.BasePay;
import com.TicketBooking.repository.BasePayRepo;
import com.TicketBooking.service.BasePayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/bpay")
public class BasePayController {

    @Autowired
    BasePayService basePayService;
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/addAll")
    public ResponseEntity<String> addCategory(@RequestBody BasePay basePay){
        return basePayService.addCategory(basePay);
    }

    @CrossOrigin(origins = "http://localhost:8082")
    @GetMapping("/all")
    public ResponseEntity<List<BasePay>> getAllCategory(){
        return basePayService.getAllCategory();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/category/{category}")
    public ResponseEntity<List<BasePay>> getByCategory(@PathVariable String category){
        return basePayService.getByCategory(category);
    }
}
