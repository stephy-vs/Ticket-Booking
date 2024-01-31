package com.TicketBooking.repository;

import com.TicketBooking.model.BasePay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasePayRepo extends JpaRepository<BasePay,Integer> {
    List<BasePay>findByCategory(String category);
}
