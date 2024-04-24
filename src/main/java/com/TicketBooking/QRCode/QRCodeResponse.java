package com.TicketBooking.QRCode;

import lombok.Data;

@Data
public class QRCodeResponse {
    private byte[] qrCodeImage;
    private String userDetails;
    private String errorVariable;
}
