package com.TicketBooking.razorPay.verifyPaymentID;

import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.stereotype.Service;

@Service
public class VerifyPaymentService {
    private static final String KEY = "rzp_test_Lh738g2oARGFbD";
    private static final String Key_Secret = "iOSGwx2YAmHsl2dNuzfi1bSa";
    private static final String currency = "INR";
    public boolean verifyPayment(String orderId, String paymentId, String signature) throws RazorpayException {
       try {
           if (!verifySignature(orderId,paymentId,signature)){
               throw new RazorpayException("Signature Verification Failed");
           }

           RazorpayClient razorpayClient =new RazorpayClient(KEY,Key_Secret);

           Payment payment =razorpayClient.payments.fetch(paymentId);

           return "captured".equals(payment.get("status"));

       }catch (RazorpayException e){
           e.printStackTrace();
           throw new RazorpayException("Payment verification failed.", e);
       }
    }

    public boolean verifySignature(String orderId,String paymentId,String signature) {
//        String Key_Secret = "iOSGwx2YAmHsl2dNuzfi1bSa";
//        String generatedSignature = orderId + " | " + paymentId + "|";
        String generatedSignature = orderId + "|" + paymentId ;
        generatedSignature = HmacUtils.hmacSha256Hex(Key_Secret, generatedSignature);
//        generatedSignature = HmacUtils.hmacSha256Hex(Key_Secret, generatedSignature);
        //generatedSignature = HmacUtils.hmacSha256Hex(Key_Secret, generatedSignature);

        System.out.println("Generated Signature: " + generatedSignature);
        System.out.println("Received Signature: " + signature);
        return generatedSignature.equals(signature);
    }
}
