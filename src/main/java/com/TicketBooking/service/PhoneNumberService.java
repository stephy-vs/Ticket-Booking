package com.TicketBooking.service;

import com.TicketBooking.model.PhoneNumber;
import com.TicketBooking.repository.PhoneNumberRepo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PhoneNumberService {
    @Autowired
    PhoneNumberRepo phoneNumberRepo;
    public ResponseEntity<String> getNumber(PhoneNumber phoneNumber) {
        phoneNumberRepo.save(phoneNumber);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    public ResponseEntity<String> sendOTP(Long phNumber) {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        String api_key = "a6207229-b6e5-11ee-8cbb-0200cd936042";

       String url ="https://2factor.in/API/V1/"+api_key+ "/SMS/" +phNumber+"/AUTOGEN3/OTP1";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        try (Response response = client.newCall(request).execute()){
            if (!response.isSuccessful()){
                throw new RuntimeException("Unexpected response code: "+response);
            }
            return new ResponseEntity<>("OTP send to the registered mobile Number ",HttpStatus.OK);

        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to send SMS ",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> verifyOTP(String apiKey, String phNumber, String otp) throws Exception{
        OkHttpClient client = new OkHttpClient();
        String api_key = "a6207229-b6e5-11ee-8cbb-0200cd936042";

        String url = "https://2factor.in/API/V1/"+api_key+"/SMS/VERIFY3/"+ phNumber+"/"+ otp;
        Request request = new Request.Builder().url(url).get().build();
        try (Response response = client.newCall(request).execute()){

            if (!response.isSuccessful()){

                throw new RuntimeException("Unexpected response code : "+response);
            }
            String responseBody = response.body().string();

            PhoneNumber phoneNumber =new PhoneNumber();
            phoneNumber.setPhNumber(phNumber);
            phoneNumberRepo.save(phoneNumber);

            return new ResponseEntity<>("Verification result : success",HttpStatus.OK);



        }


    }
}
