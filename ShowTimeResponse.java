package com.TicketBooking.DateTimeSlot.calendarEvent;

import com.TicketBooking.DateTimeSlot.showTime.ShowTime;

import java.util.List;

public class ShowTimeResponse {
    private List<ShowTime> showTimes;

    public ShowTimeResponse(List<ShowTime> showTimes) {
        this.showTimes = showTimes;
    }

    public List<ShowTime> getShowTimes() {
        return showTimes;
    }

    public void setShowTimes(List<ShowTime> showTimes) {
        this.showTimes = showTimes;
    }
}
