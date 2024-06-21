package com.jsp.pharmacy.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jsp.pharmacy.entity.Medicine;
import com.jsp.pharmacy.enums.BookingStatus;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Component
public class BookingDto {

	private int bookingId;
	private LocalDate orderDate;
	private int quantity;
	private String paymentMode;
	private LocalDate expectedDate;
	private BookingStatus  bookingStatus;
	
	@ManyToMany 
	private List<Medicine> medicines;
	
	@ManyToOne
	private CustomerDto customerDto;

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public LocalDate getExpectedDate() {
		return expectedDate;
	}

	public void setExpectedDate(LocalDate expectedDate) {
		this.expectedDate = expectedDate;
	}

	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	

	public CustomerDto getCustomerDto() {
		return customerDto;
	}

	public void setCustomerDto(CustomerDto customerDto) {
		this.customerDto = customerDto;
	}

	public List<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}

	
	
	
	
	
	
}
