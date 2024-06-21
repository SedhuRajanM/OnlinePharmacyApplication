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

import com.jsp.pharmacy.entity.Medicine;
import com.jsp.pharmacy.service.MedicineService;
import com.jsp.pharmacy.util.ResponseStructure;

@RestController
@RequestMapping("/medicine")
public class MedicineController {

	@Autowired
	private MedicineService service;

	@PostMapping
	public ResponseEntity<ResponseStructure<Medicine>> addMedicinetoMedicalStore(@RequestParam int storeId,
			@RequestBody Medicine medicine){
		return service.addMedicines(storeId,medicine);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Medicine>> updateMedicine(@RequestParam int medicineId,
			@RequestBody Medicine medicine){

		return service.updateMedicine(medicineId,medicine);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<Medicine>> findMedicine(@RequestParam int medicineId){

		return service.findMedicine(medicineId);
	} 

	@DeleteMapping
	public ResponseEntity<ResponseStructure<Medicine>> deleteMedicine(@RequestParam int medicineId){

		return service.deleteMedicine(medicineId);
	}
	

	@GetMapping("/findbyname")
	public ResponseEntity<ResponseStructure<Medicine>> findMedicineByName(@RequestParam String medicineName){

		return service.findMedicine(medicineName);
	} 
}
