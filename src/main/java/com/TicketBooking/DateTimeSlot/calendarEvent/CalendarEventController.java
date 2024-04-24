package com.TicketBooking.DateTimeSlot.calendarEvent;

import com.TicketBooking.DateTimeSlot.LockSeat.getDateData.GetDateData;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/calEve")
@CrossOrigin(origins = "http://localhost:8081")
@Slf4j
public class CalendarEventController {
    @Autowired
    private CalendarEventService calendarEventService;
    //@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate selectedDate
    @Autowired
    private CalendarRepo calendarRepo;
    @PostMapping(path = "/eventCal")
    public ResponseEntity<List<CalendarEvent>> createCalendar(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        try {
            return calendarEventService.createCalendar(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @PostMapping(path = "getNew")
    public ResponseEntity<List<CalendarEventResponse>> createCapacity(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        try {
            ResponseEntity<List<CalendarEventResponse>> response = calendarEventService.getCalendar(date);
            return response;

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<List<CalendarEvent>>getCalendarEventData(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        try {
            List<CalendarEvent> events = calendarEventService.getCalendarData(date);
            return ResponseEntity.ok(events);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "getDate")
    public ResponseEntity<?>getEventData(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        try {
            List<CalendarEvent> events = calendarRepo.findByStartDate(date);
            if (!events.isEmpty()){
                List<GetDateData> dateData = calendarEventService.listSlotData(date);
                log.info("info of dateData :"+dateData);
                return ResponseEntity.ok(dateData);
            }else {
                //return calendarEventService.createCalendar1(date);
                ResponseEntity<List<CalendarEvent>> calendarEventList = calendarEventService.createCalendar(date);
                return ResponseEntity.ok(calendarEventList);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
