package com.jsp.pharmacy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.jsp.pharmacy.dto.AdminDto;
import com.jsp.pharmacy.entity.Admin;
import com.jsp.pharmacy.service.AdminService;
import com.jsp.pharmacy.util.ResponseStructure;

@RestController
public class AdminController {

	@Autowired
	private AdminService service;

	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<AdminDto>>  signupAdmin(@RequestBody Admin admin ) {
		return service.signupAdmin(admin);
	}

	@GetMapping("/find")
	public ResponseEntity<ResponseStructure<AdminDto>> fetchAdminById(@RequestParam int id){
		return service.findAdminById(id);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<AdminDto>> updateAdminById(@RequestParam int id,@RequestBody Admin admin){
		return service.updateAdmin(id,admin);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<ResponseStructure<AdminDto>> deleteAdminById(@RequestParam int id){
		return service.deleteAdminById(id);
	}

	@GetMapping("/findall")
	public ResponseEntity<ResponseStructure<List<AdminDto>>> fetchAllAdmins(){
		return service.findAllAdmins();
	}

	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<AdminDto>> loginAdmim(@RequestParam String email,@RequestParam String password){

		return service.loginAdmin(email,password);
	}

	@PostMapping("/resetadminpassword")
	public ResponseEntity<ResponseStructure<AdminDto>> resetAdmimPassword(@RequestParam long phoneNumber, @RequestParam String email,@RequestParam String newPassword){

		return service.resetAdminPassword(phoneNumber,email,newPassword);
	}

}
