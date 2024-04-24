package com.TicketBooking.DateTimeSlot.calendarEvent;

import com.TicketBooking.DateTimeSlot.LockSeat.Booking;
import com.TicketBooking.DateTimeSlot.LockSeat.BookingRepo;
import com.TicketBooking.DateTimeSlot.LockSeat.getDateData.GetDateData;
import com.TicketBooking.DateTimeSlot.LockSeat.getDateData.GetDateDataRepo;
import com.TicketBooking.DateTimeSlot.showTime.ShowTime;
import com.TicketBooking.DateTimeSlot.showTime.ShowTimeRepo;
import com.TicketBooking.DateTimeSlot.showTime.ShowTimeService;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class CalendarEventService {
    @Autowired
    private ShowTimeRepo showTimeRepo;
    @Autowired
    private CalendarRepo calendarRepo;

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private ShowTimeService showTimeService;
    @Autowired
    private GetDateDataRepo getDateDataRepo;


//    public List<CalendarEvent> createCalendar(LocalDate date) {
//        List<CalendarEvent> excal = calendarRepo.findByStartDate(date);
//        if (!excal.isEmpty()){
//            LocalDate startDate = excal.get(0).getStartDate();
//            List<CalendarEvent> calendarEventsAll = new ArrayList<>();
//            for (CalendarEvent event :excal){
//                if (event.getStartDate().equals(startDate)){
//                    calendarEventsAll.add(event);
//                }
//            }
//            List<CalendarEventResponse> calendarEventResponses = calendarEventsAll.stream()
//                    .map(event -> new CalendarEventResponse(event.getId(),event.getStartTime(),event.getCapacity()))
//                    .collect(Collectors.toList());
//            List<CalendarEvent> calendarEventsToReturn = convertToCalendarEvents(calendarEventResponses);
//            return calendarEventsToReturn;
//        }
//        return Collections.emptyList();
//    }
//
//    private List<CalendarEvent> convertToCalendarEvents(List<CalendarEventResponse> calendarEventResponses) {
//        return calendarEventResponses.stream()
//                .map(response -> new CalendarEvent(response.getId(), response.getStartTime(),response.getCapacity()))
//                .collect(Collectors.toList());
//    }
    public ResponseEntity<List<CalendarEvent>> createCalendar(LocalDate date) {
        List<CalendarEvent> excal = calendarRepo.findByStartDate(date);
        if (!excal.isEmpty()){
            LocalDate startDate =excal.get(0).getStartDate();
            List<CalendarEvent> calendarEventsAll = new ArrayList<>();
            for (CalendarEvent event : excal){
                if (event.getStartDate().equals(startDate)){
                    calendarEventsAll.add(event);
                }
            }
            return ResponseEntity.ok(calendarEventsAll);
        }else {
            List<CalendarEvent> savedEvent1 = new ArrayList<>();
            ShowTime showTime = showTimeRepo.findById(1).orElseThrow();
            ShowTime showTime1 = showTimeRepo.findById(2).orElseThrow();
            ShowTime showTime2 = showTimeRepo.findById(3).orElseThrow();
            CalendarEvent calendarEvent1 = new CalendarEvent();
            calendarEvent1.setStartDate(date);
            LocalDate endT1 =calendarEvent1.getStartDate().plusDays(30);
            calendarEvent1.setEndDate(endT1);
            calendarEvent1.setStartTime(showTime.getStartTime());
            calendarEvent1.setEndTime(showTime.getEndTime());
            calendarEvent1.setCapacity(showTime.getCapacity());
            CalendarEvent saveEnt1=calendarRepo.save(calendarEvent1);
            savedEvent1.add(saveEnt1);
            CalendarEvent calendarEvent2 = new CalendarEvent();
            calendarEvent2.setStartDate(date);
            LocalDate end2 =calendarEvent2.getStartDate().plusDays(30);
            calendarEvent2.setEndDate(end2);
            calendarEvent2.setStartTime(showTime1.getStartTime());
            calendarEvent2.setEndTime(showTime1.getEndTime());
            calendarEvent2.setCapacity(showTime1.getCapacity());
            CalendarEvent saveEv2=calendarRepo.save(calendarEvent2);
            savedEvent1.add(saveEv2);
            CalendarEvent calendarEvent3 = new CalendarEvent();
            calendarEvent3.setStartDate(date);
            calendarEvent3.setEndDate(calendarEvent3.getStartDate().plusDays(30));
            calendarEvent3.setStartTime(showTime2.getStartTime());
            calendarEvent3.setEndTime(showTime2.getEndTime());
            calendarEvent3.setCapacity(showTime2.getCapacity());
            CalendarEvent saveEv3 = calendarRepo.save(calendarEvent3);
            savedEvent1.add(saveEv3);
            return ResponseEntity.ok(savedEvent1);
        }

    }

    public ResponseEntity<List<CalendarEventResponse>> getCalendar(LocalDate date) {
        try {
            List<CalendarEvent> calendarEvents = calendarRepo.findByStartDate(date);
            if (!calendarEvents.isEmpty()){
                List<CalendarEventResponse> eventResponses = calendarEvents.stream()
                        .map(event -> new CalendarEventResponse(event.getId(),event.getStartTime(),event.getCapacity()))
                        .collect(Collectors.toList());
                return new ResponseEntity<>(eventResponses,HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();

        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public List<CalendarEvent> getCalendarData(LocalDate date) {


        return calendarRepo.findByStartDate(date);
    }

    public List<GetDateData> listSlotData(LocalDate date) {
        try {
            List<GetDateData> getDateDataResponse = getDateDataRepo.findByStartDate(date);
            List<GetDateData> getDateDataList = getDateDataResponse.stream()
                    .map(event1 -> new GetDateData(event1.getStartDate(),event1.getCapacity(),event1.getSlotName()))
                    .collect(Collectors.toList());
            return getDateDataList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
