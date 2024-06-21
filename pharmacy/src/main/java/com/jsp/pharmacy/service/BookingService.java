package com.jsp.pharmacy.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.pharmacy.dao.BookingDao;
import com.jsp.pharmacy.dao.CustomerDao;
import com.jsp.pharmacy.dao.MedicineDao;
import com.jsp.pharmacy.dto.BookingDto;
import com.jsp.pharmacy.dto.CustomerDto;
import com.jsp.pharmacy.entity.Bookings;
import com.jsp.pharmacy.entity.Customer;
import com.jsp.pharmacy.entity.Medicine;
import com.jsp.pharmacy.enums.BookingStatus;
import com.jsp.pharmacy.exception.BookingAlreadyCancelledException;
import com.jsp.pharmacy.exception.BookingAlreadyDelivered;
import com.jsp.pharmacy.exception.BookingCantCancel;
import com.jsp.pharmacy.exception.BookingsIdNotFoundException;
import com.jsp.pharmacy.exception.CustomerIdNotFoundException;
import com.jsp.pharmacy.exception.MedicineIdNotFoundException;
import com.jsp.pharmacy.util.ResponseStructure;

@Service
public class BookingService {

	@Autowired
	private BookingDao dao;

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private MedicineDao medicineDao;

	@Autowired
	private ModelMapper mapper;

	public ResponseEntity<ResponseStructure<BookingDto>> addBooking(int customerId, int medicineId, Bookings bookings) {
		LocalDate exceptedDate=LocalDate.now().plusDays(5);
		Customer dbCustomer= customerDao.findCustomer(customerId);

		if(dbCustomer!=null) {
			bookings.setOrderDate(LocalDate.now());
			bookings.setExpectedDate(exceptedDate);
			bookings.setBookingStatus(BookingStatus.ACTIVE);
			bookings.setCustomer(dbCustomer);


			Medicine dbmedicine=medicineDao.findMedicine(medicineId);

			List<Medicine> medicines=new ArrayList<>();

			if(dbmedicine!=null) {
				medicines.add(dbmedicine);
				bookings.setMedicine(medicines);
				Bookings dbbookings=dao.addBooking(bookings);

				//taking customer old booking also adding new booking
				List<Bookings> bookingslist=dbCustomer.getBookings();
				bookingslist.add(dbbookings);

				//reseting booking in customer
				dbCustomer.setBookings(bookingslist);
				customerDao.updateCustomer(customerId,dbCustomer);

				BookingDto bookingDto=this.mapper.map(dbbookings, BookingDto.class);
				bookingDto.setMedicines(medicines);
				bookingDto.setCustomerDto(this.mapper.map(dbbookings.getCustomer(), CustomerDto.class));

				ResponseStructure<BookingDto> structure=new ResponseStructure<>();
				structure.setMessage("Booked Successfully");
				structure.setHttpstatus(HttpStatus.CREATED.value());
				structure.setData(bookingDto);

				return new ResponseEntity<ResponseStructure<BookingDto>>(structure,HttpStatus.CREATED);
			}
			else
				throw new MedicineIdNotFoundException("Soory failed to add booking");		
		}

		else
			throw new CustomerIdNotFoundException("Sorry failed to add booking");
	}


	public ResponseEntity<ResponseStructure<BookingDto>> cancelBooking(int bookingId) {
		Bookings dbBookings =dao.findBooking(bookingId);

		if(dbBookings!=null) {

			//booking Id is present

			LocalDate cantCancelDate=dbBookings.getExpectedDate().minusDays(2);

			//checking it already cancelled or not
			if(dbBookings.getBookingStatus().equals(BookingStatus.CANCELLED)) 
				throw new BookingAlreadyCancelledException("Soory failed to cancel the booking");


			else if(dbBookings.getBookingStatus().equals(BookingStatus.DELIVERED))
				throw new BookingAlreadyDelivered("Sorry failed to cancel the booking");

			else if(LocalDate.now().equals(cantCancelDate) || LocalDate.now().isAfter(cantCancelDate))
				throw new BookingCantCancel("Sorry failed to cancel the booking");

			else {
				Bookings deletedBooking=dao.deleteBooking(bookingId);

				BookingDto bookingDto=this.mapper.map(dbBookings, BookingDto.class);
				bookingDto.setMedicines(dbBookings.getMedicine());
				bookingDto.setCustomerDto(this.mapper.map(dbBookings.getCustomer(), CustomerDto.class));

				ResponseStructure<BookingDto> structure=new ResponseStructure<>();
				structure.setMessage("Booking cancelled Successfully");
				structure.setHttpstatus(HttpStatus.FORBIDDEN.value());
				structure.setData(bookingDto);

				return new ResponseEntity<ResponseStructure<BookingDto>>(structure,HttpStatus.FORBIDDEN);

			}
		}

		else
			throw new BookingsIdNotFoundException("Sorry falied to cancel the booking");

	}
}
