package com.TicketBooking.InstitutionRegistration;

import com.TicketBooking.InstitutionRegistration.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstitutionRepo extends JpaRepository<Institution,Integer> {
    

    Optional<Institution> findByOrderId(String orderId);


    Optional<Institution> findByIdAndPhoneNumber(Integer id, String phoneNumber);


    Institution findByPaymentId(String paymentId);
}
