package com.jsp.pharmacy.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.pharmacy.entity.MedicalStore;
import com.jsp.pharmacy.repo.MedicalStoreRepo;

@Repository
public class MedicalStoreDao {

	@Autowired
	private MedicalStoreRepo repo;

	public MedicalStore saveMedicalStore(MedicalStore medicalStore) {

		return repo.save(medicalStore);
	}

	public MedicalStore updateMedicalStore(int storeId, MedicalStore medicalStore) {
		Optional<MedicalStore> optional=repo.findById(storeId);
		if(optional.isPresent()) {
			MedicalStore dbmedicalStore =optional.get();
			medicalStore.setStoreId(storeId);
			//updating relationship entity 
			medicalStore.setAddress(dbmedicalStore.getAddress());
			medicalStore.setAdmin(dbmedicalStore.getAdmin());
			
			return repo.save(medicalStore);

		}
		return null;
	}

	public MedicalStore findMedicalStore(int storeId) {
		Optional<MedicalStore> optional =repo.findById(storeId);

		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public MedicalStore deleteMedicalStore(int storeId) {
		Optional<MedicalStore> optional =repo.findById(storeId);

		if(optional.isPresent()) {
			repo.deleteById(storeId);
			return optional.get();
		}
		return null;
	}
}
