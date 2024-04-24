package com.TicketBooking.DateTimeSlot.LockSeat;

import com.TicketBooking.DateTimeSlot.LockSeat.getDateData.GetDateData;
import com.TicketBooking.DateTimeSlot.LockSeat.getDateData.GetDateDataRepo;
import com.TicketBooking.DateTimeSlot.calendarEvent.CalendarEvent;
import com.TicketBooking.DateTimeSlot.calendarEvent.CalendarRepo;
import com.TicketBooking.InstitutionRegistration.Institution;
import com.TicketBooking.InstitutionRegistration.InstitutionRepo;
import com.TicketBooking.userRegistration.User;
import com.TicketBooking.userRegistration.UserRepo;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j

public class BookingService {
    @Autowired
    private CalendarRepo calendarRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    InstitutionRepo institutionRepo;
    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private GetDateDataRepo getDateDataRepo;

    public Integer lockCapacity(Integer capacity, LocalDate bookDate, LocalTime slotName,String category) {
        CalendarEvent calendarEvent =calendarRepo.findByCapacityGreaterThanEqualAndStartDateAndStartTime(capacity,bookDate,slotName);
        log.info("lockCapacity"+calendarEvent);
        if (calendarEvent ==null){
            throw new IllegalArgumentException("Capacity is Overflowing ");
        } else {
            Booking booking1 = new Booking();
            booking1.setCategory(category);
            booking1.setTickets(capacity);
            booking1.setBookDate(bookDate);
            booking1.setSlotName(slotName);
            booking1.setBookTime(LocalDateTime.now());
            booking1.setExpireTime(booking1.getBookTime().plusMinutes(2));
            booking1.setAvailable(true);
            bookingRepo.save(booking1);

//            GetDateData getDateData = new GetDateData();
//            getDateData.setBookingId(booking1.getBookingId());
//            log.info("getDateData bookingId:"+getDateData.getBookingId());
//            getDateDataRepo.save(getDateData);
//            log.info("getDateData Repo:"+getDateData);

            if ("institution".equals(booking1.getCategory())){
                Institution institution = new Institution();
                institution.setCategory(category);
                institution.setBookingId(booking1.getBookingId());
                institution.setBookDate(booking1.getBookDate());
                institution.setSlotName(booking1.getSlotName());
                institutionRepo.save(institution);
            }else {
                User user = new User();
                user.setCategory(booking1.getCategory());
                user.setBookingId(booking1.getBookingId());
                user.setBookDate(booking1.getBookDate());
                user.setSlotName(booking1.getSlotName());
                userRepo.save(user);
            }
            log.info("Inside " + booking1);
            return booking1.getBookingId();
        }
    }
    public Integer confirmBooking(Integer BookingId){
        Booking booking = bookingRepo.findFirstByBookingIdAndAvailableTrue(BookingId);
        log.info("available : "+booking.isAvailable()+" id : "+booking.getBookingId());

        if (booking!=null && booking.isAvailable()) {
            booking.setAvailable(false);
            bookingRepo.save(booking);
            Integer cap = booking.getTickets();
            LocalDate staDate = booking.getBookDate();
            LocalTime sName = booking.getSlotName();
            GetDateData getDateData = new GetDateData();

            //getDateData.setBookingId(booking.getBookingId());
            CalendarEvent calendarEvent1 = calendarRepo.findByCapacityGreaterThanEqualAndStartDateAndStartTime(cap,staDate,sName);
            if (calendarEvent1!=null){
                Integer capa= calendarEvent1.getCapacity();
                log.info("capacity :"+ capa);
                log.info("capacity :"+ cap);
//                getDateData.setStartDate(staDate);
//                getDateData.setSlotName(sName);
//                getDateData.setCapacity(capa-cap);

//                GetDateData savedData = getDateDataRepo.save(getDateData);
                //Integer getDId = getDateData.getBookingId();

                calendarEvent1.setCapacity(capa-cap);
                calendarRepo.save(calendarEvent1);


//                log.info("calendarEvent1 :"+calendarEvent1);
//                log.info("before delete Booking :"+booking);
//                log.info("bookingId: "+savedData.getGetDateDataId());
//                return savedData.getGetDateDataId();

            }
            return booking.getBookingId();

        }
        else {
            throw new IllegalStateException("Booking already confirmed");
        }

//        return null;
    }
    //@Transactional
    public void releaseExpiredBookings(){
        try {
            List<Booking> expiredBookings = bookingRepo.findByExpireTimeBefore(LocalDateTime.now());
            log.info("Number of expired booking: "+expiredBookings.size());
            for (Booking expiredBooking : expiredBookings){
//                Integer bId = expiredBooking.getBookingId();
//                GetDateData getDateData = getDateDataRepo.findByBookingId(bId);

//                GetDateData getDateData =getDateDataRepo.findBookingIdByGetDateDataId(getDateDataId);
//                Integer bId = getDateData.getBookingId();
                Integer bId = expiredBooking.getBookingId();

                if ("institution".equals(expiredBooking.getCategory())){
                    Institution institution = institutionRepo.findByBookingId(bId);

                    if (institution!=null && expiredBooking.getBookingId()==institution.getBookingId()){
                        String paymentId = institution.getPaymentId();
                        if (paymentId == null){
                            LocalDate bookDate= institution.getBookDate();
                            LocalTime slotName = institution.getSlotName();
                            CalendarEvent calendarEvent = calendarRepo.findFirstByStartDateAndStartTime(bookDate,slotName);
                            log.info("return cal :"+calendarEvent);
                            if (calendarEvent != null && expiredBooking.getTickets()!=null){
                                Integer capacity = calendarEvent.getCapacity();
                                Integer ticket = expiredBooking.getTickets();
//                                getDateData.setCapacity(capacity+ticket);
//                                getDateDataRepo.save(getDateData);
                                calendarEvent.setCapacity(capacity+ticket);
                                calendarRepo.save(calendarEvent);


                                log.info("beforeSave : "+calendarEvent);
                                bookingRepo.delete(expiredBooking);
                            }
                        }else {
                            log.info(paymentId+ " payment Id is present");
                            Booking booking = bookingRepo.findByBookingId(bId);
                            log.info("delete bookingId in Institution: "+booking);
                            bookingRepo.delete(booking);
                        }
                    }else {
                        log.info("booking id : "+bId+" is null");
                    }
                }else {
                    User user = userRepo.findByBookingId(bId);
                    if (user!=null && expiredBooking.getBookingId()== user.getBookingId()){
                        String paymentId = user.getPaymentId();
                        if (paymentId ==null){
                            LocalDate bookDate = user.getBookDate();
                            LocalTime slotName = user.getSlotName();
                            CalendarEvent calendarEvent =calendarRepo.findFirstByStartDateAndStartTime(bookDate,slotName);
                            log.info("return CalendarEvent :"+calendarEvent);
                            if (calendarEvent!=null && expiredBooking.getTickets()!=null){
                                Integer capacity = calendarEvent.getCapacity();
                                Integer ticket = expiredBooking.getTickets();
                                calendarEvent.setCapacity(capacity+ticket);
                                calendarRepo.save(calendarEvent);
//                                getDateData.setCapacity(capacity+ticket);
//                                getDateDataRepo.save(getDateData);
                                log.info("Save calendarEvent :"+calendarEvent);
                                log.info("before Delete Booking :"+expiredBooking);
                                bookingRepo.delete(expiredBooking);
                            }
                        }else {
                            log.info("payment id of user");
                            Booking booking = bookingRepo.findByBookingId(bId);
                            log.info("delete bookId: "+booking);
                            bookingRepo.delete(booking);
                        }
                    }else {
                        log.info("Booking id is null");
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
