package com.jsp.pharmacy.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.pharmacy.dao.AddressDao;
import com.jsp.pharmacy.dao.CustomerDao;
import com.jsp.pharmacy.dto.AddressDto;
import com.jsp.pharmacy.dto.BookingDto;
import com.jsp.pharmacy.dto.CustomerDto;
import com.jsp.pharmacy.entity.Address;
import com.jsp.pharmacy.entity.Bookings;
import com.jsp.pharmacy.entity.Customer;
import com.jsp.pharmacy.exception.AddressIdNotFoundException;
import com.jsp.pharmacy.exception.CustomerEmailNotFoundException;
import com.jsp.pharmacy.exception.CustomerIdNotFoundException;
import com.jsp.pharmacy.exception.CustomerInvalidPasswordException;
import com.jsp.pharmacy.exception.CustomerInvalidPhoneNumber;
import com.jsp.pharmacy.util.ResponseStructure;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao dao;

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private ModelMapper mapper;

	public ResponseEntity<ResponseStructure<CustomerDto>> signUpCustomer(int addressId, Customer customer) {
		Address dbAddress =addressDao.findAddress(addressId);
		if(dbAddress!=null) {


			//creating list adding the the dbaddress
			List<Address> addresses=new ArrayList<>();
			addresses.add(dbAddress);

			//method only accept list of addresses updating the address 
			customer.setAddresses(addresses);

			Customer dbCustomer= dao.signUpCustomer(customer);

			//updating address entity details
			dbAddress.setCustomer(dbCustomer);
			addressDao.updateAddress(addressId, dbAddress);

			CustomerDto customerDto= this.mapper.map(dbCustomer, CustomerDto.class);
			
			//list that accept AddressDto
			List<AddressDto> addressDtos=new ArrayList<>();

			//fetching the address from the list and coverting into Addressdto
			for(Address address:addresses) {
				AddressDto addressDto=this.mapper.map(address,AddressDto.class);
				addressDtos.add(addressDto);
			}

			customerDto.setAddressDtos(addressDtos);

			ResponseStructure<CustomerDto> structure=new ResponseStructure<>();
			structure.setMessage("Customer SignedUp Successfully");
			structure.setHttpstatus(HttpStatus.CREATED.value());
			structure.setData(customerDto);

			return new  ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.CREATED);
		}
		else 
			throw new AddressIdNotFoundException("Sorry failed to signup");
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> findCustomer(int customerId) {
		Customer dbCustomer =dao.findCustomer(customerId);

		if(dbCustomer!=null) {
			//frtching all address from the customer
			List<Address> addresses= dbCustomer.getAddresses();

			CustomerDto customerDto= this.mapper.map(dbCustomer, CustomerDto.class);
			//list that accept AddressDto
			List<AddressDto> addressDtos=new ArrayList<>();

			//fetching the address from the list and coverting into Addressdto
			for(Address address:addresses) {
				AddressDto addressDto=this.mapper.map(address,AddressDto.class);
				addressDtos.add(addressDto);
			}

			customerDto.setAddressDtos(addressDtos);

			ResponseStructure<CustomerDto> structure=new ResponseStructure<>();
			structure.setMessage("Customer fetched Successfully");
			structure.setHttpstatus(HttpStatus.FOUND.value());
			structure.setData(customerDto);

			return new  ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.FOUND);
		}
		else 
			throw new CustomerIdNotFoundException("Sorry failed to fetch customer");

	}

	public ResponseEntity<ResponseStructure<CustomerDto>> deleteCustomer(int customerId) {
		Customer dbCustomer=dao.deleteCustomer(customerId);

		if(dbCustomer!=null) {
			//frtching all address from the customer
			List<Address> addresses= dbCustomer.getAddresses();

			CustomerDto customerDto= this.mapper.map(dbCustomer, CustomerDto.class);
			//list that accept AddressDto
			List<AddressDto> addressDtos=new ArrayList<>();

			//fetching the address from the list and coverting into Addressdto
			for(Address address:addresses) {
				AddressDto addressDto=this.mapper.map(address,AddressDto.class);
				addressDtos.add(addressDto);
			}

			customerDto.setAddressDtos(addressDtos);

			ResponseStructure<CustomerDto> structure=new ResponseStructure<>();
			structure.setMessage("Customer deleted Successfully");
			structure.setHttpstatus(HttpStatus.FORBIDDEN.value());
			structure.setData(customerDto);

			return new  ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.FORBIDDEN);
		}
		else 
			throw new CustomerIdNotFoundException("Sorry failed to delete customer");

	}

	public ResponseEntity<ResponseStructure<CustomerDto>> updateCustomer(int customerId, Customer customer) {
		Customer dbCustomer=dao.updateCustomer(customerId, customer);
		if(dbCustomer!=null) {
			
			CustomerDto customerDto= this.mapper.map(dbCustomer, CustomerDto.class);
			
			
			List<Address> addresses=dbCustomer.getAddresses();
			
			List<AddressDto> addressDtos=new ArrayList<>();

			//fetching the address from the list and coverting into Addressdto
			for(Address address:addresses) {
				AddressDto addressDto=this.mapper.map(address,AddressDto.class);
				addressDtos.add(addressDto);
			}
			
			customerDto.setAddressDtos(addressDtos);
			
			
			List<Bookings> bookings=dbCustomer.getBookings();
			
			List<BookingDto> bookingDtos=new ArrayList<>();
			
			for(Bookings booking:bookings) {
				BookingDto bookingDto=this.mapper.map(booking,BookingDto.class);
				bookingDtos.add(bookingDto);
			}
			customerDto.setBookingDtos(bookingDtos);
			
			ResponseStructure<CustomerDto> structure=new ResponseStructure<>();
			structure.setMessage("Customer Update Successfully");
			structure.setHttpstatus(HttpStatus.OK.value());
			structure.setData(customerDto);

			return new  ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.OK);
			
		}
		else
			throw new CustomerIdNotFoundException("Sorry failed to update the Cutomer");
		
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> loginCustomer(String email, String password) {
		Customer dbCustomer =dao.findCustomerByEmail(email);
		
		if(dbCustomer!=null) {
			
			if(password.equals(dbCustomer.getPassword())) {
				
				CustomerDto customerDto= this.mapper.map(dbCustomer, CustomerDto.class);
				
				
				List<Address> addresses=dbCustomer.getAddresses();
				
				List<AddressDto> addressDtos=new ArrayList<>();

				//fetching the address from the list and coverting into Addressdto
				for(Address address:addresses) {
					AddressDto addressDto=this.mapper.map(address,AddressDto.class);
					addressDtos.add(addressDto);
				}
				
				customerDto.setAddressDtos(addressDtos);
				
				
				List<Bookings> bookings=dbCustomer.getBookings();
				
				List<BookingDto> bookingDtos=new ArrayList<>();
				
				for(Bookings booking:bookings) {
					BookingDto bookingDto=this.mapper.map(booking,BookingDto.class);
					bookingDtos.add(bookingDto);
				}
				customerDto.setBookingDtos(bookingDtos);
				
				ResponseStructure<CustomerDto> structure=new ResponseStructure<>();
				structure.setMessage("Customer login Successfull");
				structure.setHttpstatus(HttpStatus.FOUND.value());
				structure.setData(customerDto);

				return new  ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.FOUND);
				
				
			}
			
			else
				throw new CustomerInvalidPasswordException("Sorry failed to login");
		}
		else
			throw new CustomerEmailNotFoundException("Sorry failed to login");
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> resetPassword(long phoneNumber, String email,
			String newPassword) {
		Customer dbCustomer=dao.findCustomerByEmail(email);
		
		if(dbCustomer!=null) {
			
			if(dbCustomer.getPhoneNumber()==phoneNumber) {
				dbCustomer.setPassword(newPassword);
				dao.updateCustomer(dbCustomer.getCustomerId(), dbCustomer);
				
				CustomerDto customerDto= this.mapper.map(dbCustomer, CustomerDto.class);
				
				ResponseStructure<CustomerDto> structure=new ResponseStructure<>();
				structure.setMessage("Customer Password reset Successfull");
				structure.setHttpstatus(HttpStatus.FOUND.value());
				structure.setData(customerDto);
				
				return new  ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.FOUND);
			}
			else 
				throw new CustomerInvalidPhoneNumber("Soryy failed to reset the password");
		}
		else 
			throw new CustomerEmailNotFoundException("Soryy failed to reset the Password");
	}
}
