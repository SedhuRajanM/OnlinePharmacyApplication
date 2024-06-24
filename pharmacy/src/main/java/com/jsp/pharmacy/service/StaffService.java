package com.jsp.pharmacy.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.pharmacy.dao.AdminDao;
import com.jsp.pharmacy.dao.MedicalStoreDao;
import com.jsp.pharmacy.dao.StaffDao;
import com.jsp.pharmacy.dto.AddressDto;
import com.jsp.pharmacy.dto.AdminDto;
import com.jsp.pharmacy.dto.MedicalStoreDto;
import com.jsp.pharmacy.dto.StaffDto;
import com.jsp.pharmacy.entity.Admin;
import com.jsp.pharmacy.entity.MedicalStore;
import com.jsp.pharmacy.entity.Staff;
import com.jsp.pharmacy.exception.AdminIdNotFoundException;
import com.jsp.pharmacy.exception.MedicalStoreIdNotFoundException;
import com.jsp.pharmacy.exception.StaffIdNotFoundException;
import com.jsp.pharmacy.exception.StaffInvalidEmailException;
import com.jsp.pharmacy.exception.StaffInvalidPasswordException;
import com.jsp.pharmacy.exception.StaffInvalidPhoneNumber;
import com.jsp.pharmacy.util.ResponseStructure;

@Service
public class StaffService {

	@Autowired
	private StaffDao dao;
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private MedicalStoreDao medicalStoreDao;
	
	
	@Autowired
	private ModelMapper mapper;

	public ResponseEntity<ResponseStructure<StaffDto>> signUpStaff(int adminId, int storeId, Staff staff) {
		Admin dbAdmin =adminDao.fetchAdminById(storeId);
		
		if(dbAdmin!=null) {
			
			staff.setAdmin(dbAdmin);
			MedicalStore dbMedicalStore =medicalStoreDao.findMedicalStore(storeId);
			if(dbMedicalStore!=null) {
				
				//setting the Admin and medicalStore to the staff
				
				staff.setMedicalStore(dbMedicalStore);
				
				Staff dbstaff=dao.saveStaff(staff);
				
				StaffDto staffDto=this.mapper.map(dbstaff, StaffDto.class);
				
				//model mapper not convert the relationship entity
				AdminDto adminDto=this.mapper.map(dbstaff.getAdmin(), AdminDto.class);
				MedicalStoreDto medicalStoreDto=this.mapper.map(dbstaff.getMedicalStore(), MedicalStoreDto.class);
				
				
				staffDto.setAdminDto(adminDto);
				staffDto.setMedicalStoreDto(medicalStoreDto);
				
				//response structure 
				ResponseStructure<StaffDto> structure=new ResponseStructure<>();
				structure.setMessage("Staff data saved successfully");
				structure.setHttpstatus(HttpStatus.CREATED.value());
				structure.setData(staffDto);
				
				return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.CREATED);
			}
			else
				throw new MedicalStoreIdNotFoundException("Sorry failed to add staff");
		}
		else
			throw new AdminIdNotFoundException("Sorry failed to add Staff");
	}

	public ResponseEntity<ResponseStructure<StaffDto>> updateStaff(int staffId, Staff staff) {
		Staff dbstaff=dao.updateStaff(staffId,staff);
		
		if(dbstaff!=null) {
			//staff updated successfully
			
			StaffDto staffDto=this.mapper.map(dbstaff, StaffDto.class);
			
			//model mapper not convert the relationship entity 
			AdminDto adminDto=this.mapper.map(dbstaff.getAdmin(), AdminDto.class);
			
			//medicalstore dto contains addressdto so setting addressdto to medicalstoredto
			MedicalStoreDto medicalStoreDto=this.mapper.map(dbstaff.getMedicalStore(), MedicalStoreDto.class);
			
			
			staffDto.setAdminDto(adminDto);
			staffDto.setMedicalStoreDto(medicalStoreDto);
			
			//response structure 
			ResponseStructure<StaffDto> structure=new ResponseStructure<>();
			structure.setMessage("Staff updated  successfully");
			structure.setHttpstatus(HttpStatus.OK.value());
			structure.setData(staffDto);
			
			return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.OK);
		}
		else
			throw new StaffIdNotFoundException("Sorry failed to update the data");
		
	}

	public ResponseEntity<ResponseStructure<StaffDto>> findStaff(int staffId) {
		Staff dbstaff= dao.findStaff(staffId);
		

		if(dbstaff!=null) {
			//staff updated successfully
			
			StaffDto staffDto=this.mapper.map(dbstaff, StaffDto.class);
			
			//model mapper not convert the relationship entity 
			AdminDto adminDto=this.mapper.map(dbstaff.getAdmin(), AdminDto.class);
			
			MedicalStoreDto medicalStoreDto=this.mapper.map(dbstaff.getMedicalStore(), MedicalStoreDto.class);
			
			
			staffDto.setAdminDto(adminDto);
			staffDto.setMedicalStoreDto(medicalStoreDto);
			
			//response structure 
			ResponseStructure<StaffDto> structure=new ResponseStructure<>();
			structure.setMessage("Staff fetched  successfully");
			structure.setHttpstatus(HttpStatus.FOUND.value());
			structure.setData(staffDto);
			
			return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.FOUND);
		}
		else
			throw new StaffIdNotFoundException("Sorry failed to fetch the data");
	}

	public ResponseEntity<ResponseStructure<StaffDto>> deleteStaff(int staffId) {
		Staff dbstaff=dao.deleteStaff(staffId);

		if(dbstaff!=null) {
			//staff updated successfully
			
			StaffDto staffDto=this.mapper.map(dbstaff, StaffDto.class);
			
			//model mapper not convert the relationship entity 
			AdminDto adminDto=this.mapper.map(dbstaff.getAdmin(), AdminDto.class);
			MedicalStoreDto medicalStoreDto=this.mapper.map(dbstaff.getMedicalStore(), MedicalStoreDto.class);
		
			
			staffDto.setAdminDto(adminDto);
			staffDto.setMedicalStoreDto(medicalStoreDto);
			
			//response structure 
			ResponseStructure<StaffDto> structure=new ResponseStructure<>();
			structure.setMessage("Staff Deleted  successfully");
			structure.setHttpstatus(HttpStatus.FORBIDDEN.value());
			structure.setData(staffDto);
			
			return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.FORBIDDEN);
		}
		else
			throw new StaffIdNotFoundException("Sorry failed to delete the data");
	}

	public ResponseEntity<ResponseStructure<StaffDto>> loginStaff(String email, String password) {
		Staff dbstaff=dao.findByEmail(email);
		
		if(dbstaff!=null) {
			//staff updated successfully
			if(password.equals(dbstaff.getStaffPassword())) {
				
			StaffDto staffDto=this.mapper.map(dbstaff, StaffDto.class);
			
			//model mapper not convert the relationship entity 
			AdminDto adminDto=this.mapper.map(dbstaff.getAdmin(), AdminDto.class);
			MedicalStoreDto medicalStoreDto=this.mapper.map(dbstaff.getMedicalStore(), MedicalStoreDto.class);
			
			
			staffDto.setAdminDto(adminDto);
			staffDto.setMedicalStoreDto(medicalStoreDto);
			
			//response structure 
			ResponseStructure<StaffDto> structure=new ResponseStructure<>();
			structure.setMessage("Staff loginned  successfully");
			structure.setHttpstatus(HttpStatus.FOUND.value());
			structure.setData(staffDto);
			
			return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.FOUND);
			}
			
			else 
				throw new StaffInvalidPasswordException("Sorry failed to login");
		}
		else
			throw new StaffInvalidEmailException("Sorry failed to loign");
		
	}

	public ResponseEntity<ResponseStructure<StaffDto>> resetStaffPassword(long phoneNumber, String email,
			String newPassword) {
		Staff dbStaff= dao.findByEmail(email);
		
		if(dbStaff!=null) {
			
			if(dbStaff.getPhoneNumber()==phoneNumber) {
				dbStaff.setStaffPassword(newPassword);
				dao.updateStaff(dbStaff.getStaffId(), dbStaff);
				
				StaffDto staffDto=this.mapper.map(dbStaff, StaffDto.class);
				
				
				//response structure 
				ResponseStructure<StaffDto> structure=new ResponseStructure<>();
				structure.setMessage("Staff Password reset  successfull");
				structure.setHttpstatus(HttpStatus.FOUND.value());
				structure.setData(staffDto);
				
				return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.FOUND);
			}
			else
				throw new StaffInvalidPhoneNumber("sorry failed to reset the password");
		}
		else
			throw new StaffInvalidEmailException("sorry failed to rset the password");
	}
}
