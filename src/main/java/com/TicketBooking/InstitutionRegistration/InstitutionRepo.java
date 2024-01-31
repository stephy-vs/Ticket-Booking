package com.TicketBooking.InstitutionRegistration;

import com.TicketBooking.InstitutionRegistration.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionRepo extends JpaRepository<Institution,Integer> {
}
