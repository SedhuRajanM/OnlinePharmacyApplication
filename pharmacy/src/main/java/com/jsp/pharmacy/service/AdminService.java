package com.jsp.pharmacy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.pharmacy.dao.AdminDao;
import com.jsp.pharmacy.dto.AdminDto;
import com.jsp.pharmacy.entity.Admin;
import com.jsp.pharmacy.exception.AdminIdNotFoundException;

import com.jsp.pharmacy.exception.AdminInvalidtPasswordException;
import com.jsp.pharmacy.exception.AdminNotFoundWithThisEmail;
import com.jsp.pharmacy.util.ResponseStructure;

@Service
public class AdminService {

	@Autowired
	private AdminDao dao;

	//to hide the confidential data of the Admin we use dto
	@Autowired
	private AdminDto dto;

	public ResponseEntity<ResponseStructure<AdminDto>> signupAdmin(Admin admin) {
		Admin dbadmin=dao.saveAdmin(admin);

		dto.setAdminid(dbadmin.getAdminid());
		dto.setAdminName(dbadmin.getAdminName());
		dto.setAdminAddress(dbadmin.getAdminAddress());

		ResponseStructure<AdminDto> structure=new ResponseStructure<AdminDto>();
		structure.setMessage("Admin Signedup Successfully");
		structure.setHttpstatus(HttpStatus.CREATED.value());
		structure.setData(dto);
		return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.CREATED);


	}

	public ResponseEntity<ResponseStructure<AdminDto>> findAdminById(int id) {
		Admin dbadmin=dao.fetchAdminById(id);

		if(dbadmin!=null) {
			dto.setAdminid(dbadmin.getAdminid());
			dto.setAdminName(dbadmin.getAdminName());
			dto.setAdminAddress(dbadmin.getAdminAddress());

			ResponseStructure<AdminDto> structure=new ResponseStructure<AdminDto>();
			structure.setMessage("Admin Data Fetched Successfully");
			structure.setHttpstatus(HttpStatus.FOUND.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.FOUND);	
		}

		//if dbAdmin is null it throw the AdminIdNotFoundException
		else 
			throw new AdminIdNotFoundException("Sorry failed to fetch the Data");

	}

	public ResponseEntity<ResponseStructure<AdminDto>> updateAdmin(int id, Admin admin) {
		Admin dbadmin=dao.updateAdmin(id,admin);

		if(dbadmin!=null) {
			//id is present and the data is updated successfully

			dto.setAdminid(dbadmin.getAdminid());
			dto.setAdminName(dbadmin.getAdminName());
			dto.setAdminAddress(dbadmin.getAdminAddress());

			ResponseStructure<AdminDto> structure=new ResponseStructure<>();
			structure.setMessage("Data Update Successfully");
			structure.setHttpstatus(HttpStatus.OK.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.OK);	
		}

		else 
			throw new AdminIdNotFoundException("Sorry failed to update the Data");

	}

	public ResponseEntity<ResponseStructure<AdminDto>> deleteAdminById(int id) {
		Admin dbadmin =dao.deleteAdminById(id);

		if(dbadmin!=null) {

			dto.setAdminid(dbadmin.getAdminid());
			dto.setAdminName(dbadmin.getAdminName());
			dto.setAdminAddress(dbadmin.getAdminAddress());

			ResponseStructure<AdminDto> structure=new ResponseStructure<>();
			structure.setMessage("Admin Data deleted Successfully");
			structure.setHttpstatus(HttpStatus.FORBIDDEN.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.FORBIDDEN);	
		}
		else
			throw new AdminIdNotFoundException("Sorry failed to delete the Data");
	}

	public ResponseEntity<ResponseStructure<List<AdminDto>>> findAllAdmins() {
		List<Admin> list=dao.getAllAdmin();

		List<AdminDto>  adminDtos=new ArrayList<>();

		//fetching all the admin in the list
		for(Admin admin:list) {

			AdminDto admindto=new AdminDto();
			//setting admin details to dto
			admindto.setAdminid(admin.getAdminid());
			admindto.setAdminName(admin.getAdminName());
			admindto.setAdminAddress(admin.getAdminAddress());

			//adding dto in adminDto 
			adminDtos.add(admindto);
		}

		ResponseStructure<List<AdminDto>> structure=new ResponseStructure<>();
		structure.setMessage("Admins Data fetched Successfully");
		structure.setHttpstatus(HttpStatus.FOUND.value());
		structure.setData(adminDtos);
		return new ResponseEntity<ResponseStructure<List<AdminDto>>>(structure,HttpStatus.FOUND);	


	}

	public ResponseEntity<ResponseStructure<AdminDto>> loginAdmin(String email, String password) {
		Admin dbadmin=dao.findByEmail(email);

		if(dbadmin!=null) {

			if(password.equals(dbadmin.getAdminPassword())) {

				dto.setAdminid(dbadmin.getAdminid());
				dto.setAdminName(dbadmin.getAdminName());
				dto.setAdminAddress(dbadmin.getAdminAddress());
				
				ResponseStructure<AdminDto> structure=new ResponseStructure<>();
				structure.setMessage("Login Successfully");
				structure.setHttpstatus(HttpStatus.FOUND.value());
				structure.setData(dto);
				return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.FOUND);	
				
			}
			else
				throw new AdminInvalidtPasswordException("Sorry failed to login");
		}
		else 
			throw new AdminNotFoundWithThisEmail("Sorry failed to login");
	}

}
