package com.TicketBooking.DateTimeSlot.calendarEvent;

//import com.TicketBooking.DateTimeSlot.LockSeat.getDateData.GetDateData;
import com.TicketBooking.constant.TicketConstants;
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
@CrossOrigin(origins = TicketConstants.serverName)
@Slf4j
public class CalendarEventController {
    @Autowired
    private CalendarEventService calendarEventService;
    //@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate selectedDate
    @Autowired
    private CalendarRepo calendarRepo;
    @GetMapping(path = "/eventCal")
    public ResponseEntity<List<CalendarEvent>> createCalendar(@RequestParam
                                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                  LocalDate date,@RequestParam List<Integer> slotIds){
        try {
            return calendarEventService.createCalendar(date,slotIds);
        }catch (Exception e){

            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/distinctDate")
    public ResponseEntity<List<LocalDate>> getDistinctStartDate(){
        List<LocalDate> distinctData = calendarEventService.getDistinctDate();
        return ResponseEntity.ok(distinctData);
    }

//    @GetMapping(path = "/eventCal")
//    public ResponseEntity<List<CalendarEvent>> createCalendar(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
//        try {
//            return calendarEventService.createCalendar(date);
//        }catch (Exception e){
//
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @GetMapping(path = "dateData")
    public ResponseEntity<List<CalendarEvent>> getDataByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        List<CalendarEvent> events = calendarEventService.getEventDetails(date);
        return ResponseEntity.ok(events);
    }

    @PutMapping(path = "capacity/{id}")
    public ResponseEntity<String>updateCapacity(@PathVariable("id") Integer id,
                                          @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate startDate,
                                          @RequestParam("capacity") Integer capacity){
        calendarEventService.updateCapacity(id,startDate,capacity);
        return ResponseEntity.ok().build();
    }








}
