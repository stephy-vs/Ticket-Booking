package com.TicketBooking.DateTimeSlot.calendarEvent;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface CalendarRepo extends JpaRepository<CalendarEvent,Integer> {
    List<CalendarEvent> findByStartDate(LocalDate date);
//    CalendarEvent findFirstByStartDateAndCapacity(LocalDate bookDate, Integer capacity);



//    CalendarEvent findByCapacityGreaterThanEqualAndStartDate(Integer capacity, LocalDate bookDate);

    CalendarEvent findByCapacityGreaterThanEqualAndStartDateAndStartTime(Integer capacity, LocalDate bookDate, LocalTime slotName);



//    CalendarEvent findByStartDateAndStartTime(LocalDate bookDte, LocalTime sName);

    CalendarEvent findFirstByStartDateAndStartTime(LocalDate bookDte, LocalTime sName);


}
