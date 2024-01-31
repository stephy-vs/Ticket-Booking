package com.TicketBooking.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bpaytbl")
public class BasePay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "category")
    private String category;

    @Column(name = "pay")
    private Integer pay;


}
