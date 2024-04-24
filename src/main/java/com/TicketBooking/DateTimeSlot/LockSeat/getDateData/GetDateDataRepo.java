package com.TicketBooking.DateTimeSlot.LockSeat.getDateData;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface GetDateDataRepo extends JpaRepository<GetDateData,Integer> {
    GetDateData findByBookingId(Integer bId);

    List<GetDateData> findByStartDate(LocalDate date);

    GetDateData findBookingIdByGetDateDataId(Integer getDateDataId);
}
