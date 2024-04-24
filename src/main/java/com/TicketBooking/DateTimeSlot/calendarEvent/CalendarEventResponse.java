package com.TicketBooking.DateTimeSlot.calendarEvent;

import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalTime;
@Data

public class CalendarEventResponse {
    private Integer id;
    private LocalTime startTime;
    private Integer capacity;

    public CalendarEventResponse(Integer id, LocalTime startTime, Integer capacity) {
        this.id = id;
        this.startTime = startTime;
        this.capacity = capacity;
    }
}
