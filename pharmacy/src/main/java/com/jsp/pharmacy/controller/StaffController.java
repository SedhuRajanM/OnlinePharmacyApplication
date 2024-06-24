package com.jsp.pharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.pharmacy.dto.AdminDto;
import com.jsp.pharmacy.dto.StaffDto;
import com.jsp.pharmacy.entity.Staff;
import com.jsp.pharmacy.service.StaffService;
import com.jsp.pharmacy.util.ResponseStructure;

@RestController
@RequestMapping("/staff")
public class StaffController {

	@Autowired
	private StaffService service;

	@PostMapping
	public ResponseEntity<ResponseStructure<StaffDto>> signUpStaff( @RequestParam int adminId, @RequestParam int storeId ,
			@RequestBody Staff staff){
		return service.signUpStaff(adminId,storeId,staff);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<StaffDto>> updateStaff( @RequestParam int staffId,
			@RequestBody Staff staff){
		return service.updateStaff(staffId,staff);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<StaffDto>> findStaff( @RequestParam int staffId){
		return service.findStaff(staffId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<StaffDto>> deleteStaff( @RequestParam int staffId){
		return service.deleteStaff(staffId);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<StaffDto>> loginStaff(@RequestParam String email,@RequestParam String password){

		return service.loginStaff(email,password);
	}
	
	@PostMapping("/resetstaffpassword")
	public ResponseEntity<ResponseStructure<StaffDto>> resetAdmimPassword(@RequestParam long phoneNumber, @RequestParam String email,@RequestParam String newPassword){

		return service.resetStaffPassword(phoneNumber,email,newPassword);
	}
}
