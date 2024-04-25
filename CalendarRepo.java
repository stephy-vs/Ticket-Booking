package com.TicketBooking.DateTimeSlot.calendarEvent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface CalendarRepo extends JpaRepository<CalendarEvent,Integer> {
    List<CalendarEvent> findByStartDate(LocalDate date);
    CalendarEvent findByCapacityGreaterThanEqualAndStartDateAndStartTime(Integer capacity, LocalDate bookDate, LocalTime slotName);
    CalendarEvent findFirstByStartDateAndStartTime(LocalDate bookDte, LocalTime sName);



    CalendarEvent findCapacityById(int i);

    @Modifying
    @Query("UPDATE CalendarEvent  c SET c.capacity=:capacity WHERE c.startDate = :startDate AND c.id = :id")

    void updateCapacity(@Param("id") Integer id,@Param("startDate") LocalDate startDate, @Param("capacity") Integer capacity);



    @Query("SELECT DISTINCT c.startDate FROM CalendarEvent c")
    List<LocalDate> findDistinctStartDate();

    //    void updateCapacity(@Param("id") Integer id, @Param("date")LocalDate date, @Param("capacity")Integer capacity);
}
