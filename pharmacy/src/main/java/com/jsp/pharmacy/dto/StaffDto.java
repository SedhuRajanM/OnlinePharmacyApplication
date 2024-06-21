package com.jsp.pharmacy.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Component
public class StaffDto {

	private int staffId;
	private String staffName;
	private long phoneNumber;
	
	@ManyToOne
	private AdminDto adminDto;
	
	@OneToOne
	private MedicalStoreDto medicalStoreDto;

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public AdminDto getAdminDto() {
		return adminDto;
	}

	public void setAdminDto(AdminDto adminDto) {
		this.adminDto = adminDto;
	}

	public MedicalStoreDto getMedicalStoreDto() {
		return medicalStoreDto;
	}

	public void setMedicalStoreDto(MedicalStoreDto medicalStoreDto) {
		this.medicalStoreDto = medicalStoreDto;
	}
	
	
	
}
