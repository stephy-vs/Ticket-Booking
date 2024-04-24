package com.TicketBooking.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "numberTbl")
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "phNumber")
    private String phNumber;

}
