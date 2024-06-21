package com.jsp.pharmacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.pharmacy.dao.AddressDao;
import com.jsp.pharmacy.entity.Address;
import com.jsp.pharmacy.exception.AddressIdNotFoundException;
import com.jsp.pharmacy.util.ResponseStructure;

@Service
public class AddressService {

	@Autowired
	private AddressDao dao;

	public ResponseEntity<ResponseStructure<Address>> saveAddress(Address address) {

		Address dbAddress=dao.saveAddress(address);

		ResponseStructure<Address> structure=new ResponseStructure<>();
		structure.setMessage("Address Saved Succefully");
		structure.setHttpstatus(HttpStatus.CREATED.value());
		structure.setData(dbAddress);

		return new ResponseEntity<ResponseStructure<Address>>(structure,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Address>> updateAddress(int id, Address address) {
		Address dbAddress= dao.updateAddress(id,address);

		if(dbAddress!=null) {

			ResponseStructure<Address> structure=new ResponseStructure<>();
			structure.setMessage("Address updated Succefully");
			structure.setHttpstatus(HttpStatus.OK.value());
			structure.setData(dbAddress);

			return new ResponseEntity<ResponseStructure<Address>>(structure,HttpStatus.OK);
		}
		else
			throw new AddressIdNotFoundException("Sorry failed to update the data");
	}

	public ResponseEntity<ResponseStructure<Address>> findAddress(int id) {
		Address dbAddress= dao.findAddress(id);

		if(dbAddress!=null) {
			ResponseStructure<Address> structure=new ResponseStructure<>();
			structure.setMessage("Address fecthed Succefully");
			structure.setHttpstatus(HttpStatus.FOUND.value());
			structure.setData(dbAddress);

			return new ResponseEntity<ResponseStructure<Address>>(structure,HttpStatus.FOUND);
		}
		else
			throw new AddressIdNotFoundException("Sorry failed to fecthed the data");
	}

	public ResponseEntity<ResponseStructure<Address>> deleteAddress(int id) {
		Address dbAddress= dao.deleteAddress(id);
		
		if(dbAddress!=null) {
			ResponseStructure<Address> structure=new ResponseStructure<>();
			structure.setMessage("Address deleted Succefully");
			structure.setHttpstatus(HttpStatus.FORBIDDEN.value());
			structure.setData(dbAddress);

			return new ResponseEntity<ResponseStructure<Address>>(structure,HttpStatus.FORBIDDEN);
		}
		else
			throw new AddressIdNotFoundException("Sorry failed to delete the data");
	
	}
}

