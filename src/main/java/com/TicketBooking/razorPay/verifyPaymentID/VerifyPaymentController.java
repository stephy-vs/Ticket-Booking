package com.TicketBooking.razorPay.verifyPaymentID;

import com.TicketBooking.userRegistration.User;
import com.TicketBooking.userRegistration.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/verifypy")
public class VerifyPaymentController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    VerifyPaymentService verifyPaymentService;
    @PostMapping(path = "/py")
    public ResponseEntity<Object> verifyPayment(@RequestParam String orderId,
                                                @RequestParam String paymentId,
                                                @RequestParam String signature){
        try {
            boolean paymentVerified = verifyPaymentService.verifyPayment(orderId,paymentId,signature);
            if (paymentVerified){
                Optional<User> userOptional = userRepo.findByOrderId(orderId);
                User userPy = new User();
                if (userOptional != null){
                    userPy.setPaymentId(paymentId);
                    userRepo.save(userPy);
                }
            }
            return ResponseEntity.ok("Payment Successfull. OrderId : "+orderId+"Payment Id : "+paymentId);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Payment verification is failed"+e.getMessage());
        }
    }
}
