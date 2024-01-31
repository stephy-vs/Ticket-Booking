package com.TicketBooking.service;

import com.TicketBooking.model.BasePay;
import com.TicketBooking.repository.BasePayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasePayService {

    @Autowired
    BasePayRepo basePayRepo;
    public ResponseEntity<String> addCategory(BasePay basePay) {
        basePayRepo.save(basePay);
        return new ResponseEntity<>("Added ", HttpStatus.OK) ;
    }

    public ResponseEntity<List<BasePay>> getAllCategory() {
        try {
            return new ResponseEntity<>(basePayRepo.findAll(),HttpStatus.OK);


        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<BasePay>> getByCategory(String category) {
        try {
            return new ResponseEntity<>(basePayRepo.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }
}
