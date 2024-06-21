package com.jsp.pharmacy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.pharmacy.entity.Address;
import com.jsp.pharmacy.util.ResponseStructure;

@RestControllerAdvice
public class PharmacyExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> adminIdNotFound(AdminIdNotFoundException exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("ADMIN ID IS NOT PRESENT");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> adminEmailNotFound(AdminNotFoundWithThisEmail exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("...INVALID EMAIL!!!!....");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> adminIncorrectPassword(AdminInvalidtPasswordException exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("...INVALID PASSWORD!!!!....");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> addressNotFound(AddressIdNotFoundException exception){
		
		ResponseStructure<String>  structure =new ResponseStructure<>();
		structure.setMessage("...ADDRESS NOT PRESENT!!....");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> storeIdNotFound(MedicalStoreIdNotFoundException exception){
		
		ResponseStructure<String>  structure =new ResponseStructure<>();
		structure.setMessage(">>> ! STORE ID Not PRESENT ! <<<");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> medicineIdNotFound(MedicineIdNotFoundException exception){
		
		ResponseStructure<String>  structure =new ResponseStructure<>();
		structure.setMessage(">>> ! MEDICINE ID Not PRESENT ! <<<");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> medicineNameNotFound(MedicineNameNotFoundException exception){
		
		ResponseStructure<String>  structure =new ResponseStructure<>();
		structure.setMessage(">>> ! MEDICINE NAME Not PRESENT ! <<<");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> staffIdNotFound(StaffIdNotFoundException exception){
		
		ResponseStructure<String>  structure =new ResponseStructure<>();
		structure.setMessage(">>> ! STAFF ID Not PRESENT ! <<<");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> staffInvalidPassword(StaffInvalidPasswordException exception){
		
		ResponseStructure<String>  structure =new ResponseStructure<>();
		structure.setMessage(">>> ! STAFF INVALID PASSWORD ! <<<");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> staffInvalidEmail(StaffInvalidEmailException exception){
		
		ResponseStructure<String>  structure =new ResponseStructure<>();
		structure.setMessage(">>> ! STAFF INVALID EMAIL ! <<<");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> customerIdNotFound(CustomerIdNotFoundException exception){
		
		ResponseStructure<String>  structure =new ResponseStructure<>();
		structure.setMessage(">>> ! CUSTOMER INVALID ID ! <<<");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> bookingIdNotFound(BookingsIdNotFoundException exception){
		
		ResponseStructure<String>  structure =new ResponseStructure<>();
		structure.setMessage(">>> ! BOOKING  ID IS NOT PRESENT ! <<<");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> bookingAlreadyCancelled(BookingAlreadyCancelledException exception){
		
		ResponseStructure<String>  structure =new ResponseStructure<>();
		structure.setMessage(">>> ! BOOKING ALREADY CANCELLED ! <<<");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> bookingcantCancel(BookingCantCancel exception){
		
		ResponseStructure<String>  structure =new ResponseStructure<>();
		structure.setMessage(">>> ! BOOKING CANT CANCELLED ! <<<");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> bookingAlreadyDelivered(BookingAlreadyDelivered exception){
		
		ResponseStructure<String>  structure =new ResponseStructure<>();
		structure.setMessage(">>> ! BOOKING ALREADY DELIVERED ! <<<");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> customerEmailNotFound(CustomerEmailNotFoundException exception){
		
		ResponseStructure<String>  structure =new ResponseStructure<>();
		structure.setMessage(">>> ! CUSTOMER EMAIL NOT FOUND ! <<<");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> customerInvaliPassword(CustomerInvalidPasswordException exception){
		
		ResponseStructure<String>  structure =new ResponseStructure<>();
		structure.setMessage(">>> ! CUSTOMER INVALID PASSWORD ! <<<");
		structure.setHttpstatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
}
