package com.TicketBooking.repository;

import com.TicketBooking.model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneNumberRepo extends JpaRepository<PhoneNumber,Integer> {
}
