package com.jsp.pharmacy.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Component
public class MedicalStoreDto {

	private int storeId;
	private String storeName;
	private String managerName;
	private long phone;
	
	@OneToOne
	private AddressDto addressDto;
	
	@ManyToOne
	private AdminDto adminDto;

	
	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public AddressDto getAddressDto() {
		return addressDto;
	}

	public void setAddressDto(AddressDto addressDto) {
		this.addressDto = addressDto;
	}

	public AdminDto getAdminDto() {
		return adminDto;
	}

	public void setAdminDto(AdminDto adminDto) {
		this.adminDto = adminDto;
	}
	
	
	
}
