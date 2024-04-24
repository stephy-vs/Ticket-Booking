package com.TicketBooking.DateTimeSlot.showTime;

import com.TicketBooking.DateTimeSlot.showTime.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowTimeRepo extends JpaRepository<ShowTime,Integer> {
}
