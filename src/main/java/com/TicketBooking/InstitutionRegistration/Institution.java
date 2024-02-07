package com.TicketBooking.InstitutionRegistration;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "eduData")
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "teachers")
    private Integer teachers;

    @Column(name = "student")
    private Integer student;

    @Column(name = "bookDate")
    private LocalDate bookDate;

    @Column(name = "total")
    private Integer total;

    @Column(name = "district")
    private String district;

    @Column(name = "orderId")
    private String orderId;

    @Column(name = "paymentId")
    private String paymentId;


}
