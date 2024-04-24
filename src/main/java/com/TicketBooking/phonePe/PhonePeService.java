//package com.TicketBooking.phonePe;

import com.phonepe.sdk.pg.common.http.PhonePeResponse;
import com.phonepe.sdk.pg.payments.v1.models.request.PgPayRequest;
import com.phonepe.sdk.pg.payments.v1.models.response.PayPageInstrumentResponse;
import com.phonepe.sdk.pg.payments.v1.models.response.PgPayResponse;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.UUID;

//@Service
//public class PhonePeService {
//    @Autowired
//    PhonePeClient phonePeClient;
//
//    public TransactionDto createTransaction(Integer amount, Integer id, String phoneNumber) {
//        try {
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("amount",amount*100);
//            jsonObject.put("currency",Currency);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    public ResponseEntity<String> paymentIniti(String merchantId, String saltKey, Integer saltIndex) {
//        //String merchantId = "PGTESTPAYUAT";
//        String merchantTransactionId = UUID.randomUUID().toString().substring(0,34);
//        long amount =100;
//        String merchantUserId = "MUID123";
//        String callbackurl = "https://www.merchant.com/callback";
//        String redirecturl = "https://www.merchant.com/redirect";
//        String redirectMode = "REDIRECT";
//
//        PgPayRequest pgPayRequest = PgPayRequest.PayPagePayRequestBuilder()
//                .amount(amount)
//                .merchantId(merchantId)
//                .merchantTransactionId(merchantTransactionId)
//                .callbackUrl(callbackurl)
//                .merchantUserId(merchantUserId)
//                .redirectUrl(redirecturl)
//                .redirectMode(redirectMode).build();
//
//        PhonePeResponse<PgPayResponse> payResponsePhonePeResponse =phonePeClient.getPhonepeClient().pay(pgPayRequest);
//        PayPageInstrumentResponse payPageInstrumentResponse = (PayPageInstrumentResponse)payResponsePhonePeResponse.getData().getInstrumentResponse();
//        String url = payPageInstrumentResponse.getRedirectInfo().getUrl();
//        return new ResponseEntity<>("Success", HttpStatus.OK);
//    }
//}
