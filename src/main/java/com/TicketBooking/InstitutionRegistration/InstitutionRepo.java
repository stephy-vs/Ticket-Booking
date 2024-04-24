package com.TicketBooking.InstitutionRegistration;

import com.TicketBooking.InstitutionRegistration.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< Updated upstream
public interface InstitutionRepo extends JpaRepository<Institution,Integer> {
=======
import java.util.Optional;

public interface InstitutionRepo extends JpaRepository<Institution,Integer> {
    

    Optional<Institution> findByOrderId(String orderId);


    Optional<Institution> findByIdAndPhoneNumber(Integer id, String phoneNumber);


    Institution findByPaymentId(String paymentId);

    Institution findByBookingId(Integer bId);
>>>>>>> Stashed changes
}
