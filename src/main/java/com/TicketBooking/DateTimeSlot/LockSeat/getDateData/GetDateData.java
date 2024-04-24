package com.TicketBooking.DateTimeSlot.LockSeat.getDateData;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "getDate_data")
public class GetDateData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "getDateDataId")
    private Integer getDateDataId;

    @Column(name = "startDate")
    private LocalDate startDate;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "slotName")
    private LocalTime slotName;

    @Column(name = "bookingId")
    private Integer bookingId;

    public GetDateData() {
    }

    public GetDateData(LocalDate startDate, Integer capacity, LocalTime slotName) {
        this.startDate = startDate;
        this.capacity = capacity;
        this.slotName = slotName;
    }

    public GetDateData(Integer getDateDataId, LocalDate startDate, Integer capacity, LocalTime slotName, Integer bookingId) {
        this.getDateDataId = getDateDataId;
        this.startDate = startDate;
        this.capacity = capacity;
        this.slotName = slotName;
        this.bookingId = bookingId;
    }
}
