package com.TicketBooking.DateTimeSlot.showTime;

import java.time.LocalTime;

public class ShowTimeDTO {
    Integer id;
    LocalTime startTime;
    LocalTime endTime;

    Integer capacity;

    public ShowTimeDTO() {
    }

    public ShowTimeDTO(Integer id, LocalTime startTime, LocalTime endTime, Integer capacity) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
