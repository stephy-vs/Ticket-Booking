package com.TicketBooking.DateTimeSlot.LockSeat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepo extends JpaRepository<Booking,Integer> {
    Booking findFirstByBookingIdAndAvailableTrue(Integer bookingId);

    List<Booking> findByExpireTimeBefore(LocalDateTime now);

    Booking findByBookingId(Integer bId);

    List<Booking> findByBookDate(LocalDate date);


//    Booking findByExpireTimeBefore(LocalDateTime now);

//    List<Booking> findAllByExpireTimeBeforeAndAvailableTrue(LocalDateTime now);
}
