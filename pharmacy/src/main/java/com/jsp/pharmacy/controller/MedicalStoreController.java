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

import com.jsp.pharmacy.dto.MedicalStoreDto;
import com.jsp.pharmacy.entity.MedicalStore;
import com.jsp.pharmacy.service.MedicalStoreService;
import com.jsp.pharmacy.util.ResponseStructure;

@RestController
@RequestMapping("/store")
public class MedicalStoreController {

	@Autowired
	private MedicalStoreService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> establishTheMedicalStore(@RequestParam int adminId,
			@RequestParam int addressId,@RequestBody MedicalStore medicalStore){
		
		return service.establishMedicalStore(adminId,addressId,medicalStore);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> updateMedicalStore(@RequestParam int storeId,@RequestBody MedicalStore medicalStore){
		return service.updateMedicalStore(storeId,medicalStore);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> deleteMedicalStore(@RequestParam int storeId){
		return service.deleteMedicalStore(storeId);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> findMedicalStore(@RequestParam int storeId){
		return service.findMedicalStore(storeId);
	}
}
