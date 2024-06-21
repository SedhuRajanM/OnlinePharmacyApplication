package com.jsp.pharmacy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.pharmacy.entity.Bookings;

public interface BookingRepo extends JpaRepository<Bookings, Integer> {

}
