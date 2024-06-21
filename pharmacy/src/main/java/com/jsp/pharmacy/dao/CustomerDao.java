package com.jsp.pharmacy.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.pharmacy.entity.Customer;
import com.jsp.pharmacy.repo.CustomerRepo;

@Repository
public class CustomerDao {

	@Autowired
	private CustomerRepo repo;

	public Customer signUpCustomer(Customer customer) {
		
		return repo.save(customer);
	}

	public Customer findCustomer(int customerId) {
		Optional<Customer> optional=repo.findById(customerId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public Customer deleteCustomer(int customerId) {
		Optional<Customer> optional=repo.findById(customerId);
		if(optional.isPresent()) {
			repo.deleteById(customerId);
			return optional.get();
		}
		return null;
	}

	public Customer updateCustomer(int customerId, Customer dbCustomer) {
		Optional<Customer> optional=repo.findById(customerId);
		if(optional.isPresent()) {
			dbCustomer.setCustomerId(customerId);
			dbCustomer.setAddresses(optional.get().getAddresses());
			dbCustomer.setBookings(optional.get().getBookings());
			
			return repo.save(dbCustomer);
		}
		return null;
		
	}

	public Customer findCustomerByEmail(String email) {
		
		Optional<Customer> optional =repo.findByEmail(email);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
}
