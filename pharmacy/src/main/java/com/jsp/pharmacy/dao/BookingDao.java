package com.jsp.pharmacy.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.pharmacy.entity.Bookings;
import com.jsp.pharmacy.repo.BookingRepo;

@Repository
public class BookingDao {

	@Autowired
	private BookingRepo repo;

	public Bookings addBooking(Bookings bookings) {

		return repo.save(bookings);
	}

	public Bookings findBooking(int bookingId) {
		Optional<Bookings> optional= repo.findById(bookingId);

		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public Bookings deleteBooking(int bookingId) {

		Optional<Bookings> optional= repo.findById(bookingId);

		if(optional.isPresent()) {
			repo.deleteById(bookingId);
			return optional.get();
		}
		return null;
	}
}
