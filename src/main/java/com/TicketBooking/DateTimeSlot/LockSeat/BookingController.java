package com.TicketBooking.DateTimeSlot.LockSeat;

import com.TicketBooking.DateTimeSlot.calendarEvent.CalendarEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/book")
@CrossOrigin(origins = "http://localhost:8081")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping(path = "/lock")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<Integer> lockCapacity(@RequestParam Integer capacity ,@RequestParam LocalDate bookDate,
                                               @RequestParam LocalTime slotName,@RequestParam String category){
        try {
            Integer bookingId1 = bookingService.lockCapacity(capacity,bookDate,slotName,category);
              Integer bookingId = bookingService.confirmBooking(bookingId1);
            bookingService.releaseExpiredBookings();
            return ResponseEntity.ok(bookingId1);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(Integer.valueOf(0));
        }
    }
}