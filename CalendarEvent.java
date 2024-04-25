package com.TicketBooking.DateTimeSlot.calendarEvent;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "calen_eve")
public class CalendarEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "startTime")
    private LocalTime startTime;

    @Column(name = "endTime")
    private LocalTime endTime;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "startDate")
    private LocalDate startDate;

    @Column(name = "endDate")
    private LocalDate endDate;

    @Column(name = "status")
    private Boolean status;

    public CalendarEvent() {
    }

    public CalendarEvent(Integer id, LocalTime startTime, Integer capacity) {
        this.id = id;
        this.startTime = startTime;
        this.capacity = capacity;

    }

    //    @Column(name = "available")
//    private boolean available;

//    @Column(name = "bookingTime")
//    private LocalDateTime bookingTime;
}
