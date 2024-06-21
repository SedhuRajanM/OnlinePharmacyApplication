package com.jsp.pharmacy.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.pharmacy.entity.Admin;
import com.jsp.pharmacy.repo.AdminRepo;

@Repository
public class AdminDao {

	@Autowired
	private AdminRepo repo;
	
	
	public Admin saveAdmin(Admin admin) {
		return repo.save(admin);	
	}

	
	public Admin fetchAdminById(int id) {
		
		//findById method return optional class to avoid NoSuchElementFoundException
		Optional<Admin> optional = repo.findById(id);	
		
		//isPresnt() returns true if Admin is present in  the the optional  
		if(optional.isPresent()) {
			
			//get() help to get the data from the optional
			return optional.get();
		}
		return null;
	}


	public Admin updateAdmin(int id, Admin admin) {
		
		//for update first we should check id is present or not
		Optional<Admin> optional= repo.findById(id);
		if(optional.isPresent()) {
			admin.setAdminid(id);
			return repo.save(admin);
		}
		return null;
	}


	public Admin deleteAdminById(int id) {
		Optional<Admin> optional= repo.findById(id);
		
		if(optional.isPresent()) {			
			repo.deleteById(id);
			return optional.get();
		}
		
		return null;
	}


	public List<Admin> getAllAdmin() {	
		return repo.findAll();
	}


	public Admin findByEmail(String email) {
		Optional<Admin> optional=repo.findByEmail(email);
		
		if(optional.isPresent()) {
			//admin is present
			return optional.get();
		}
		return null;
	}

}
