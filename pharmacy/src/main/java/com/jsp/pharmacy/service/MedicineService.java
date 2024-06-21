package com.jsp.pharmacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.pharmacy.dao.MedicalStoreDao;
import com.jsp.pharmacy.dao.MedicineDao;
import com.jsp.pharmacy.entity.MedicalStore;
import com.jsp.pharmacy.entity.Medicine;
import com.jsp.pharmacy.exception.MedicalStoreIdNotFoundException;
import com.jsp.pharmacy.exception.MedicineIdNotFoundException;
import com.jsp.pharmacy.exception.MedicineNameNotFoundException;
import com.jsp.pharmacy.util.ResponseStructure;

@Service
public class MedicineService {

	@Autowired
	private MedicineDao dao;
	
	@Autowired
	private MedicalStoreDao storeDao;

	public ResponseEntity<ResponseStructure<Medicine>> addMedicines(int storeId, Medicine medicine) {
		MedicalStore dbmedicalStore=storeDao.findMedicalStore(storeId);
		
		if(dbmedicalStore!=null) {
			//setting the medicalstore to the medicine
			medicine.setMedicalStore(dbmedicalStore);
		Medicine dbmedicine =dao.addMedicine(medicine);
		
		ResponseStructure<Medicine> structure=new ResponseStructure<>();
		structure.setMessage("Medicine Savde Successfully");
		structure.setHttpstatus(HttpStatus.CREATED.value());
		structure.setData(dbmedicine);
		
		return new ResponseEntity<ResponseStructure<Medicine>>(structure,HttpStatus.CREATED);
		}
		else 
			throw new MedicalStoreIdNotFoundException("Sorry failed to add medicine to the medicalstore");
	}

	public ResponseEntity<ResponseStructure<Medicine>> updateMedicine(int medicineId, Medicine medicine) {
		Medicine dbmedicine=dao.updateMedicine(medicineId,medicine);
		
		if(dbmedicine!=null) {
			//data updated successfully
			ResponseStructure<Medicine> structure=new ResponseStructure<>();
			structure.setMessage("Medicine updated Successfully");
			structure.setHttpstatus(HttpStatus.OK.value());
			structure.setData(dbmedicine);
			
			return new ResponseEntity<ResponseStructure<Medicine>>(structure,HttpStatus.OK);
		}
		else
			throw new MedicineIdNotFoundException("Sorry failed to update the data");
	}

	public ResponseEntity<ResponseStructure<Medicine>> findMedicine(int medicineId) {
		Medicine dbmedicine= dao.findMedicine(medicineId);

		if(dbmedicine!=null) {
			//data fetched successfully
			ResponseStructure<Medicine> structure=new ResponseStructure<>();
			structure.setMessage("Medicine fetched Successfully");
			structure.setHttpstatus(HttpStatus.FOUND.value());
			structure.setData(dbmedicine);
			
			return new ResponseEntity<ResponseStructure<Medicine>>(structure,HttpStatus.FOUND);
		}
		else
			throw new MedicineIdNotFoundException("Sorry failed to fecth the data");
	}

	public ResponseEntity<ResponseStructure<Medicine>> deleteMedicine(int medicineId) {
		Medicine dbmedicine=dao.deleteMedicine(medicineId);
		
		if(dbmedicine!=null) {
			//data fetched successfully
			ResponseStructure<Medicine> structure=new ResponseStructure<>();
			structure.setMessage("Medicine deleted Successfully");
			structure.setHttpstatus(HttpStatus.FORBIDDEN.value());
			structure.setData(dbmedicine);
			
			return new ResponseEntity<ResponseStructure<Medicine>>(structure,HttpStatus.FORBIDDEN);
		}
		else
			throw new MedicineIdNotFoundException("Sorry failed to delete the data");
	}

	public ResponseEntity<ResponseStructure<Medicine>> findMedicine(String medicineName) {
		Medicine dbmedicine=dao.findMedicineyName(medicineName);
		if(dbmedicine!=null) {
			//data fetched successfully
			ResponseStructure<Medicine> structure=new ResponseStructure<>();
			structure.setMessage("Medicine fetched Successfully");
			structure.setHttpstatus(HttpStatus.FOUND.value());
			structure.setData(dbmedicine);
			
			return new ResponseEntity<ResponseStructure<Medicine>>(structure,HttpStatus.FOUND);
		}
		else
			throw new MedicineNameNotFoundException("Sorry failed to fecth the data");
	}
	
	
}
