package com.jsp.pharmacy.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.pharmacy.dao.AddressDao;
import com.jsp.pharmacy.dao.AdminDao;
import com.jsp.pharmacy.dao.MedicalStoreDao;
import com.jsp.pharmacy.dto.AddressDto;
import com.jsp.pharmacy.dto.AdminDto;
import com.jsp.pharmacy.dto.MedicalStoreDto;
import com.jsp.pharmacy.entity.Address;
import com.jsp.pharmacy.entity.Admin;
import com.jsp.pharmacy.entity.MedicalStore;
import com.jsp.pharmacy.exception.AddressIdNotFoundException;
import com.jsp.pharmacy.exception.AdminIdNotFoundException;
import com.jsp.pharmacy.exception.MedicalStoreIdNotFoundException;
import com.jsp.pharmacy.util.ResponseStructure;
@Service
public class MedicalStoreService {

	
	@Autowired
	private MedicalStoreDao dao;
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private ModelMapper mapper;

	public ResponseEntity<ResponseStructure<MedicalStoreDto>> establishMedicalStore(int adminId, int addressId,
			MedicalStore medicalStore) {
		
		Admin dbAdmin= adminDao.fetchAdminById(adminId);
		
		if(dbAdmin!=null) {
			//i have set the admin to the medicalstore
			medicalStore.setAdmin(dbAdmin);
			
			//i want to check whether that address is present or not
			Address dbAddress= addressDao.findAddress(addressId);
			
			if(dbAddress!=null) {
				//dbaddress is present
				medicalStore.setAddress(dbAddress);
				
				MedicalStore dbMedicalStore= dao.saveMedicalStore(medicalStore);
				//to set the medical store to the this particular address
				dbAddress.setMedicalStore(medicalStore);
				
				//update the address
				addressDao.updateAddress(addressId, dbAddress);
				
				MedicalStoreDto dto=new MedicalStoreDto();
				dto.setStoreId(dbMedicalStore.getStoreId());
				dto.setStoreName(dbMedicalStore.getStoreName());
				dto.setManagerName(dbMedicalStore.getManagerName());
				dto.setPhone(dbMedicalStore.getPhone());
				
				Address address=dbMedicalStore.getAddress();
				
				AddressDto addressDto=new AddressDto();
				addressDto.setAddressId(address.getAddressId());
				addressDto.setStreetName(address.getStreetName());
				addressDto.setState(address.getState());
				addressDto.setPincode(address.getPincode());
				addressDto.setCity(address.getCity());
				
				dto.setAddressDto(addressDto);
				
				
				Admin admin=dbMedicalStore.getAdmin();
				
				AdminDto adminDto=new AdminDto();
				adminDto.setAdminAddress(admin.getAdminAddress());
				adminDto.setAdminid(admin.getAdminid());
				adminDto.setAdminName(admin.getAdminName());
				
				dto.setAdminDto(adminDto);
				
				ResponseStructure<MedicalStoreDto> structure=new ResponseStructure<>();
				structure.setMessage("MedicalStore established Successfully");
				structure.setHttpstatus(HttpStatus.CREATED.value());
				structure.setData(dto);
				
				return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure,HttpStatus.CREATED);
			}
			
			else
				throw new AddressIdNotFoundException("Sorry failed to establish the medical store");
		}
		else
			throw new AdminIdNotFoundException("Sorry failed to establish the medical store");
	}

	public ResponseEntity<ResponseStructure<MedicalStoreDto>> updateMedicalStore(int storeId,
			MedicalStore medicalStore) {
		
		MedicalStore dbmedicalStore =dao.updateMedicalStore(storeId,medicalStore);
		
		if(dbmedicalStore!=null) {
			//updated successfully
			
			//mapper used to convert medicalstore to medicalstoredto 
			MedicalStoreDto medicalStoreDto=this.mapper.map(dbmedicalStore, MedicalStoreDto.class);
					
			//mapper not care about relationship entity so converting other entity also
			AddressDto addressDto=this.mapper.map(dbmedicalStore.getAddress(), AddressDto.class);
			AdminDto adminDto=this.mapper.map(dbmedicalStore.getAdmin(), AdminDto.class);
			
			medicalStoreDto.setAddressDto(addressDto);
			medicalStoreDto.setAdminDto(adminDto);
						
			ResponseStructure<MedicalStoreDto> structure= new ResponseStructure<>();
			structure.setMessage("MedicalStore data updated succesfully");
			structure.setHttpstatus(HttpStatus.OK.value());
			structure.setData(medicalStoreDto);
			
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure,HttpStatus.OK);
		}
		else 
			throw new MedicalStoreIdNotFoundException("Sorry failed to update the MedicalStore");
	}

	public ResponseEntity<ResponseStructure<MedicalStoreDto>> findMedicalStore(int storeId) {
		MedicalStore dbmedicalStore= dao.findMedicalStore(storeId);

		if(dbmedicalStore!=null) {
			//fetched successfully
			
			//mapper used to convert medicalstore to medicalstoredto 
			MedicalStoreDto medicalStoreDto=this.mapper.map(dbmedicalStore, MedicalStoreDto.class);
					
			//mapper not care about relationship entity so converting other entity also
			AddressDto addressDto=this.mapper.map(dbmedicalStore.getAddress(), AddressDto.class);
			AdminDto adminDto=this.mapper.map(dbmedicalStore.getAdmin(), AdminDto.class);
			
			medicalStoreDto.setAddressDto(addressDto);
			medicalStoreDto.setAdminDto(adminDto);
						
			ResponseStructure<MedicalStoreDto> structure= new ResponseStructure<>();
			structure.setMessage("MedicalStore data fetched succesfully");
			structure.setHttpstatus(HttpStatus.FOUND.value());
			structure.setData(medicalStoreDto);
			
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure,HttpStatus.FOUND);
		}
		else 
			throw new MedicalStoreIdNotFoundException("Sorry failed to fetch the MedicalStore");
	}

	public ResponseEntity<ResponseStructure<MedicalStoreDto>> deleteMedicalStore(int storeId) {
		
		MedicalStore dbmedicalStore= dao.deleteMedicalStore(storeId);
		
		if(dbmedicalStore!=null) {
			//deleted successfully
			
			//mapper used to convert medicalstore to medicalstoredto 
			MedicalStoreDto medicalStoreDto=this.mapper.map(dbmedicalStore, MedicalStoreDto.class);
					
			//mapper not care about relationship entity so converting other entity also
			AddressDto addressDto=this.mapper.map(dbmedicalStore.getAddress(), AddressDto.class);
			AdminDto adminDto=this.mapper.map(dbmedicalStore.getAdmin(), AdminDto.class);
			
			medicalStoreDto.setAddressDto(addressDto);
			medicalStoreDto.setAdminDto(adminDto);
						
			ResponseStructure<MedicalStoreDto> structure= new ResponseStructure<>();
			structure.setMessage("MedicalStore data deleted succesfully");
			structure.setHttpstatus(HttpStatus.FORBIDDEN.value());
			structure.setData(medicalStoreDto);
			
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure,HttpStatus.FORBIDDEN);
		}
		else 
			throw new MedicalStoreIdNotFoundException("Sorry failed to delete the MedicalStore");
	
	}
}
