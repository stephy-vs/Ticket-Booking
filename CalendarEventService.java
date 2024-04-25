package com.TicketBooking.DateTimeSlot.calendarEvent;

import com.TicketBooking.DateTimeSlot.LockSeat.Booking;
import com.TicketBooking.DateTimeSlot.LockSeat.BookingRepo;
//import com.TicketBooking.DateTimeSlot.LockSeat.getDateData.GetDateData;
//import com.TicketBooking.DateTimeSlot.LockSeat.getDateData.GetDateDataRepo;
import com.TicketBooking.DateTimeSlot.showTime.ShowTime;
import com.TicketBooking.DateTimeSlot.showTime.ShowTimeRepo;
import com.TicketBooking.DateTimeSlot.showTime.ShowTimeService;
import com.TicketBooking.InstitutionRegistration.Institution;
import com.TicketBooking.InstitutionRegistration.InstitutionRepo;
import com.TicketBooking.userRegistration.User;
import com.TicketBooking.userRegistration.UserRepo;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Slf4j
@Service
public class CalendarEventService {
    @Autowired
    private ShowTimeRepo showTimeRepo;
    @Autowired
    private CalendarRepo calendarRepo;
    @Autowired
    private InstitutionRepo institutionRepo;
    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private ShowTimeService showTimeService;
    @Autowired
    private UserRepo userRepo;

    public ResponseEntity<List<CalendarEvent>> createCalendar(LocalDate date,List<Integer> slotIds) {
        List<CalendarEvent> excal = calendarRepo.findByStartDate(date);
        if (!excal.isEmpty()){
            LocalDate startDate =excal.get(0).getStartDate();
            List<CalendarEvent> calendarEventsAll = new ArrayList<>();
            for (CalendarEvent event : excal){
                if (event.getStartDate().equals(startDate)){
                    List<Booking> expiredBookings = bookingRepo.findByExpireTimeBefore(LocalDateTime.now());
                    for (Booking expiredBooking : expiredBookings){
                        Integer bId = expiredBooking.getBookingId();
                        if ("institution".equals(expiredBooking.getCategory())){
                            Institution institution = institutionRepo.findByBookingId(bId);
                            Integer expId = expiredBooking.getBookingId();
                            Integer institId = institution.getBookingId();
                            if (expId.equals(institId)){
                                String paymentId = institution.getPaymentId();
                                if (paymentId == null){
                                    LocalDate bookDate= institution.getBookDate();
                                    LocalTime slotName = institution.getSlotName();
                                    event = calendarRepo.findFirstByStartDateAndStartTime(bookDate,slotName);
                                    if (event!= null && expiredBooking.getTickets()!=null){
                                        Integer capacity = event.getCapacity();
                                        Integer ticket = expiredBooking.getTickets();
                                        event.setCapacity(capacity+ticket);
                                        calendarRepo.save(event);
                                        bookingRepo.delete(expiredBooking);
                                    }
                                }else {
                                    log.info("payment id is present");

                                    Booking booking = bookingRepo.findByBookingId(institution.getBookingId());
                                    bookingRepo.delete(booking);
                                }
                            }else {
                                Booking booking = bookingRepo.findByBookingId(bId);
                                bookingRepo.delete(booking);
                            }
                        }else {
                            User user = userRepo.findByBookingId(bId);
                            Integer expId = expiredBooking.getBookingId();
                            Integer userId = user.getBookingId();
                            if (expId.equals(userId)){
                                String paymentId = user.getPaymentId();
                                if (paymentId == null){
                                    LocalDate bookDate = user.getBookDate();
                                    LocalTime slotName = user.getSlotName();
                                    event = calendarRepo.findFirstByStartDateAndStartTime(bookDate,slotName);
                                    if (event!= null && expiredBooking.getTickets()!=null){
                                        Integer capacity = event.getCapacity();
                                        Integer ticket = expiredBooking.getTickets();
                                        event.setCapacity(capacity+ticket);
                                        calendarRepo.save(event);
                                        bookingRepo.delete(expiredBooking);
                                    }
                                }else {
                                    log.info("payment id is present");
                                }
                            }
                        }
                    }
                    calendarEventsAll.add(event);
                }
            }
            return ResponseEntity.ok(calendarEventsAll);
        }else {
            List<CalendarEvent> savedEvent1 = new ArrayList<>();
//            ShowTime showTime1 = showTimeRepo.findById(1).orElseThrow();
//            ShowTime showTime2 = showTimeRepo.findById(2).orElseThrow();
//            ShowTime showTime3 = showTimeRepo.findById(3).orElseThrow();
//
//            CalendarEvent calendarEvent1 = new CalendarEvent();
//            calendarEvent1.setStartDate(date);
//            LocalDate endT1 =calendarEvent1.getStartDate().plusDays(30);
//            calendarEvent1.setEndDate(endT1);
//            calendarEvent1.setStartTime(showTime1.getStartTime());
//            calendarEvent1.setEndTime(showTime1.getEndTime());
//            calendarEvent1.setCapacity(showTime1.getCapacity());
//            calendarEvent1.setStatus(showTime1.getStatus());
//            CalendarEvent saveEnt1=calendarRepo.save(calendarEvent1);
//            savedEvent1.add(saveEnt1);
//
//            CalendarEvent calendarEvent2 = new CalendarEvent();
//            calendarEvent2.setStartDate(date);
//            LocalDate end2 =calendarEvent2.getStartDate().plusDays(30);
//            calendarEvent2.setEndDate(end2);
//            calendarEvent2.setStartTime(showTime2.getStartTime());
//            calendarEvent2.setEndTime(showTime2.getEndTime());
//            calendarEvent2.setCapacity(showTime2.getCapacity());
//            calendarEvent2.setStatus(showTime2.getStatus());
//            CalendarEvent saveEv2=calendarRepo.save(calendarEvent2);
//            savedEvent1.add(saveEv2);
//
//            CalendarEvent calendarEvent3 = new CalendarEvent();
//            calendarEvent3.setStartDate(date);
//            calendarEvent3.setEndDate(calendarEvent3.getStartDate().plusDays(30));
//            calendarEvent3.setStartTime(showTime3.getStartTime());
//            calendarEvent3.setEndTime(showTime3.getEndTime());
//            calendarEvent3.setCapacity(showTime3.getCapacity());
//            calendarEvent3.setStatus(showTime3.getStatus());
//            CalendarEvent saveEv3 = calendarRepo.save(calendarEvent3);
//            savedEvent1.add(saveEv3);
            for (Integer slotId : slotIds){
                ShowTime showTime = showTimeRepo.findById(slotId).orElseThrow();
                if (showTime.getStatus()){
                    CalendarEvent calendarEvent = new CalendarEvent();
                    calendarEvent.setStartDate(date);
                    LocalDate endDate = calendarEvent.getStartDate().plusDays(1);
                    calendarEvent.setEndDate(endDate);
                    calendarEvent.setStartTime(showTime.getStartTime());
                    calendarEvent.setEndTime(showTime.getEndTime());
                    calendarEvent.setCapacity(showTime.getCapacity());
                    calendarEvent.setStatus(showTime.getStatus());
                    CalendarEvent savedEvent = calendarRepo.save(calendarEvent);
                    savedEvent1.add(savedEvent);
                }else {
                    log.info("Slot with ID {} is disabled and will not be added to the calendar."+ slotId);
                }
            }
            return ResponseEntity.ok(savedEvent1);
        }
    }

    public List<CalendarEvent> getEventDetails(LocalDate date) {
        return calendarRepo.findByStartDate(date);
    }

    @Transactional
    public ResponseEntity<String> updateCapacity(Integer id, LocalDate startDate, Integer capacity) {
        try {
            calendarRepo.updateCapacity(id,startDate,capacity);
            return new ResponseEntity<>("capacity updated",HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("capacity not updated",HttpStatus.BAD_REQUEST);
    }

    public List<LocalDate> getDistinctDate() {
        return calendarRepo.findDistinctStartDate();
    }
}






