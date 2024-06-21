package com.jsp.pharmacy.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.pharmacy.entity.Staff;

public interface StaffRepo extends JpaRepository<Staff, Integer> {

	@Query("Select st from Staff st  where st.staffEmail=?1")
	Optional<Staff> findByEmail(String email);

}
