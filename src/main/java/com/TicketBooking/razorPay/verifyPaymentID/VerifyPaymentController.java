package com.TicketBooking.razorPay.verifyPaymentID;

import com.TicketBooking.InstitutionRegistration.Institution;
import com.TicketBooking.InstitutionRegistration.InstitutionRepo;
import com.TicketBooking.userRegistration.User;
import com.TicketBooking.userRegistration.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/verifypy")
public class VerifyPaymentController {

    private static final String KEY = "rzp_test_Lh738g2oARGFbD";
    private static final String Key_Secret = "iOSGwx2YAmHsl2dNuzfi1bSa";
    private static final String currency = "INR";


    @Autowired
    UserRepo userRepo;
    @Autowired
    InstitutionRepo institutionRepo;
    @Autowired
    VerifyPaymentService verifyPaymentService;

    @Autowired
    public VerifyPaymentController(VerifyPaymentService verifyPaymentService,
                                   UserRepo userRepo,
                                   InstitutionRepo institutionRepo){
        this.verifyPaymentService =verifyPaymentService;
        this.userRepo =userRepo;
        this.institutionRepo = institutionRepo;

    }
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping(path = "/py")
    public ResponseEntity<Object> verifyPayment(@RequestBody VerifyPaymentDto verifyPaymentDto){
        try {
            boolean paymentVerified = verifyPaymentService.verifyPayment(verifyPaymentDto.getOrderId(),
                    verifyPaymentDto.getPaymentId(), verifyPaymentDto.getSignature());
            if (paymentVerified){
                Optional<User> userOptional = userRepo.findByOrderId(verifyPaymentDto.getOrderId());
                Optional<Institution> institutionOptional =institutionRepo.
                        findByOrderId(verifyPaymentDto.getOrderId());

                User userEntity = userOptional.orElse(null);
                Institution institutionEntity = institutionOptional.orElse(null);


                if (userEntity != null){
                    userEntity.setPaymentId(verifyPaymentDto.getPaymentId());
                    userRepo.save(userEntity);
                } else if (institutionEntity!=null) {
                    institutionEntity.setPaymentId(verifyPaymentDto.getPaymentId());
                    institutionRepo.save(institutionEntity);
                }else {
                    return ResponseEntity.badRequest().body("No corresponding order details found." +
                            " order id "+verifyPaymentDto.getOrderId());
                }
                return ResponseEntity.ok("Payment Successfull. OrderId : "+verifyPaymentDto.getOrderId()+
                        "Payment Id : "+verifyPaymentDto.getPaymentId());
            }else {
                return ResponseEntity.badRequest().body("Payment verification failed ");
            }

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Payment verification is failed"+e.getMessage());
        }
    }
}
