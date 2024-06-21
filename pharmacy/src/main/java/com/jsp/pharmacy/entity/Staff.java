package com.jsp.pharmacy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Staff {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int staffId;
	private String staffName;
	private String staffEmail;
	private String staffPassword;
	private long phoneNumber;
	
	@ManyToOne
	private Admin admin;
	
	@OneToOne
	private MedicalStore medicalStore;

	
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

	public String getStaffEmail() {
		return staffEmail;
	}

	public void setStaffEmail(String staffEmail) {
		this.staffEmail = staffEmail;
	}

	public String getStaffPassword() {
		return staffPassword;
	}

	public void setStaffPassword(String staffPassword) {
		this.staffPassword = staffPassword;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public MedicalStore getMedicalStore() {
		return medicalStore;
	}

	public void setMedicalStore(MedicalStore medicalStore) {
		this.medicalStore = medicalStore;
	}
	
	
	
	

}
