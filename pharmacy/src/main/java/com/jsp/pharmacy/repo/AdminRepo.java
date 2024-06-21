package com.jsp.pharmacy.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.pharmacy.entity.Admin;

public interface AdminRepo extends JpaRepository<Admin,Integer>{

	@Query("select admin from Admin admin where admin.adminEmail=?1")
	Optional<Admin> findByEmail(String email);

	
}
