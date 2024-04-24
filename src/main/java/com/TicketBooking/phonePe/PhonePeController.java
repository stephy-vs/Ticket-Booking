package com.TicketBooking.phonePe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping(path = "/peApi")
//@ResponseBody
//public class PhonePeController {
//    @Autowired
//    private PhonePeService phonePeService;
//    @GetMapping("/payinit")
//
//    public ResponseEntity<TransactionDto> initiatePayment(@PathVariable Integer amount,
//                                                  @PathVariable Integer id,
//                                                  @PathVariable String phoneNumber){
//
//        TransactionDto transactionDto = phonePeService.createTransaction(amount,id,phoneNumber);
//        if (transactionDto !=null){
//            return new ResponseEntity<>(transactionDto, HttpStatus.OK);
//        }else {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        //return phonePeService.paymentIniti(merchantId,saltKey,saltIndex);
//    }
//}
