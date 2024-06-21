package com.jsp.pharmacy.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.pharmacy.entity.Address;
import com.jsp.pharmacy.repo.AddressRepo;

@Repository
public class AddressDao {

	@Autowired
	private AddressRepo repo;

	public Address saveAddress(Address address) {

		return repo.save(address);
	}

	public Address updateAddress(int id, Address address) {
		Optional<Address> optional = repo.findById(id);

		if(optional.isPresent()) {
			address.setAddressId(id);
			return repo.save(address);
		}
		return null;
	}

	public Address findAddress(int id) {
		Optional<Address> optional = repo.findById(id);

		if(optional.isPresent()) {
			return optional.get();
		}
		
		return null;
	}

	public Address deleteAddress(int id) {
		
		Optional<Address> optional = repo.findById(id);

		if(optional.isPresent()) {		
			repo.deleteById(id);
			return optional.get();
		}
		
		return null;
	}
}
