package com.jsp.pharmacy.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.pharmacy.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{

	@Query("select cu from Customer cu where cu.customerEmail=?1")
	Optional<Customer> findByEmail(String email);

}
