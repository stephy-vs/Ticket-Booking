package com.TicketBooking.controller;

import com.TicketBooking.model.PhoneNumber;
import com.TicketBooking.service.PhoneNumberService;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bookingTicket")

public class PhoneNumberController {

    @Autowired
    PhoneNumberService phoneNumberService;

    @PostMapping(path = "getNumber")
    public ResponseEntity<String> numberGet(@RequestBody PhoneNumber phoneNumber){
        return phoneNumberService.getNumber(phoneNumber);
    }

    @CrossOrigin(origins = "http://localhost:8080")

    @GetMapping(path = "/sendSms{phNumber}")
    public ResponseEntity<String> sendOTP(@RequestParam Long phNumber){
        return phoneNumberService.sendOTP(phNumber);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/verify")
    public ResponseEntity<String> verifyOTP(@RequestParam String api_key,
                                            @RequestParam String phNumber,
                                            @RequestParam String otp) {
        try {
            ResponseEntity<String> verificationResult = phoneNumberService.verifyOTP(api_key, phNumber, otp);
            return verificationResult;
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Exception occurred : "+e.getMessage());
        }        //return phoneNumberService.verifyOTP(api_key,phNumber,otp);

    }

}
