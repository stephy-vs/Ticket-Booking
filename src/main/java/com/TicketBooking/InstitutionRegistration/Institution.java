package com.TicketBooking.InstitutionRegistration;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "eduData")
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "phNum")
    private String phNum;

    @Column(name = "email")
    private String email;

    @Column(name = "teachers")
    private Integer teachers;

    @Column(name = "student")
    private Integer student;

    @Column(name = "bookDate")
    private LocalDate bookDate;

    @Column(name = "category")
    private String category;

    @Column(name = "total")
    private Integer total;

    @Column(name = "district")
    private String district;

<<<<<<< Updated upstream
=======
    @Column(name = "orderId")
    private String orderId;

    @Column(name = "paymentId")
    private String paymentId;

    @Column(name = "bookingId")
    private Integer bookingId;

    @Column(name = "slotName")
    private LocalTime slotName;


>>>>>>> Stashed changes
}
