package com.TicketBooking.userRegistration;

import com.TicketBooking.userRegistration.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {

    Optional<User> findByOrderId(String orderId);

    User findByPaymentId(String paymentId);


    Optional<User> findByIdAndPhoneNumber(Integer id, String phoneNumber);

    User findByBookingId(Integer bId);
}
