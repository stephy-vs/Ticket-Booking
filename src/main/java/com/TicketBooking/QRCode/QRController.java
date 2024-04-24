package com.TicketBooking.QRCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/qrApi")
public class QRController {

    @Autowired
    QRService qrService;

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/qrPrint")
    public ResponseEntity<?> bookTicket(@RequestBody QRData qrData){
        try {
            QRCodeResponse response = qrService.generateAndFetchQrCode(qrData);
            return ResponseEntity.ok().header("Content-Type", MediaType.APPLICATION_JSON_VALUE).body(response);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to process booking");
        }
    }

}
