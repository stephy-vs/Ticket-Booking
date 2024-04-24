package com.TicketBooking.razorPay;

import com.TicketBooking.InstitutionRegistration.Institution;
import com.TicketBooking.InstitutionRegistration.InstitutionRepo;
import com.TicketBooking.userRegistration.User;
import com.TicketBooking.userRegistration.UserRepo;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDetailService {

    private static final String ORDER_PLACED = "Placed";
    private static final String KEY = "rzp_test_Lh738g2oARGFbD";
    private static final String Key_Secret = "iOSGwx2YAmHsl2dNuzfi1bSa";
    private static final String Currency = "INR";

    @Autowired
    UserRepo userRepo;
    @Autowired
    InstitutionRepo institutionRepo;

    public TransactionDetails createTransaction(Integer amount,Integer id,String phoneNumber){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("amount",amount * 100);
            jsonObject.put("currency",Currency);


            RazorpayClient razorpayClient = new RazorpayClient(KEY,Key_Secret);
            Order order = razorpayClient.orders.create(jsonObject);
            TransactionDetails transactionDetails = prepareTransactionDetails(order);


            Optional<User> optionalUser = userRepo.findByIdAndPhoneNumber(id,phoneNumber);
            Optional<Institution> optionalInstitution = institutionRepo.findByIdAndPhoneNumber(id,phoneNumber);

            User userEntity = optionalUser.orElse(null);
            Institution institutionEntity=optionalInstitution.orElse(null);

            if (userEntity!=null){
                userEntity.setOrderId(transactionDetails.getOrderId());
                userRepo.save(userEntity);
            } else if (institutionEntity!=null) {
                institutionEntity.setOrderId(transactionDetails.getOrderId());
                institutionRepo.save(institutionEntity);

            }
//            if (optionalUser.isPresent()){
//                User user = optionalUser.get();
//                user.setOrderId(transactionDetails.getOrderId());
//                userRepo.save(user);
//            }
//            else {
//
//                if (optionalInstitution.isPresent()){
//                    Institution institution = optionalInstitution.get();
//                    institution.setOrderId(transactionDetails.getOrderId());
//                    institutionRepo.save(institution);
//                }
//            }


            return transactionDetails;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    private TransactionDetails prepareTransactionDetails(Order order){
       try {
           String orderId = order.get("id");
           String currency = order.get("currency");

           Integer amount = order.get("amount");
           String key = order.get("key");
           Integer uniqueId = order.get("uniqueId");


           TransactionDetails transactionDetails = new TransactionDetails(orderId,currency,amount,KEY);

           return transactionDetails;
       }catch (Exception e){
           e.printStackTrace();
       }
       return null;
    }


}
