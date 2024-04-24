package com.TicketBooking.QRCode;

import lombok.Data;

import java.time.LocalDate;

@Data
public class QRData {
    private String name;
    private Integer adult;
    private Integer child;
    private Integer teachers;
    private Integer student;
    private Integer total;
    private LocalDate bookDate;
    private String paymentId;

}
