package com.TicketBooking.admin.WeekDay;

import java.time.LocalDate;

public class UserCountDTO {
    private LocalDate visitDate;
    private String weekDay;
    private Integer count;

    public UserCountDTO(LocalDate visitDate,  Integer count) {
        this.visitDate = visitDate;
//        this.weekDay = visitDate.format(DateTimeFormatter.ofPattern("EEE", Locale.ENGLISH));
//        this.count = count;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
