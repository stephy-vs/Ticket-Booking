package com.TicketBooking.razorPay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/orderDetails")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;


    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/createTransaction/{amount}/{id}/{phoneNumber}")
    public ResponseEntity<TransactionDetails> createTransaction(@PathVariable  Integer amount,
                                                                @PathVariable Integer id,
                                                                @PathVariable String phoneNumber){
        TransactionDetails transactionDetails = orderDetailService.createTransaction(amount,id,phoneNumber);
        if (transactionDetails != null){
            return new ResponseEntity<>(transactionDetails, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //return orderDetailService.createTransaction(amount);

    }
}
