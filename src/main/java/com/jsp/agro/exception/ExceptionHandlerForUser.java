package com.jsp.agro.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.agro.util.ResponseStructure;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.OverridesAttribute;

@RestControllerAdvice
public class ExceptionHandlerForUser  extends ResponseEntityExceptionHandler{
	ResponseStructure<String> m = new ResponseStructure<String>();

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> IdNotFoundException(IdNotFoundException e) {

		m.setData("User Id Not Found");
		m.setMessage(e.getMessage());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PasswordWrongException.class)
	public ResponseEntity<ResponseStructure<String>> PasswordWrongException(PasswordWrongException e) {

		m.setData("User password is wrong");
		m.setMessage(e.getMessage());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmailWrongException.class)
	public ResponseEntity<ResponseStructure<String>> EmailWrongException(EmailWrongException e) {

		m.setData("User email is wrong");
		m.setMessage(e.getMessage());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ResponseStructure<String>> SQLIntegrityConstraintViolationException(
			SQLIntegrityConstraintViolationException e) {

		m.setData("Email already exist");
		m.setMessage(e.getMessage());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> UserNotFound(UserNotFoundException e) {
		m.setMessage("user id not found");
		m.setStatus(HttpStatus.NOT_FOUND.value());
		m.setData(e.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(m, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ImageNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> ImageNotFound(ImageNotFoundException e) {
		m.setMessage("image id not found");
		m.setStatus(HttpStatus.NOT_FOUND.value());
		m.setData(e.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(m, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PostNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> PostNotFound(PostNotFoundException e) {
		m.setMessage("post id not found");
		m.setStatus(HttpStatus.NOT_FOUND.value());
		m.setData(e.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(m, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(CommentNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> CommentNotFound(CommentNotFoundException e) {
		m.setMessage("comment id not found");
		m.setStatus(HttpStatus.NOT_FOUND.value());
		m.setData(e.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(m, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EquipmentNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> EquipmentNotFound(EquipmentNotFoundException e) {
		m.setMessage("comment id not found");
		m.setStatus(HttpStatus.NOT_FOUND.value());
		m.setData(e.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(m, HttpStatus.NOT_FOUND);
	}
	
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<ResponseStructure<Map<String, String>>> handleValidationExceptions(
//	  MethodArgumentNotValidException ex) {
//	    Map<String, String> errors = new HashMap<>();
//	    ex.getBindingResult().getAllErrors().forEach((error) -> {
//	        String fieldName = ((FieldError) error).getField();
//	        String errorMessage = error.getDefaultMessage();
//	        errors.put(fieldName, errorMessage);
//	    });
//	    m.setMessage("constraint violation");
//	    m.setStatus(HttpStatus.BAD_REQUEST.value());
//	    m.setData(errors.entrySet());
//	    return new ResponseEntity<ResponseStructure<Map<String, String>>>(HttpStatus.BAD_REQUEST) ;
//	}
	



	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<ObjectError> error = ex.getAllErrors();
		Map<String, String> map = new HashMap<String, String>();
		ResponseStructure<Object> structure = new ResponseStructure<>();

		for (ObjectError objectError : error) {
			String filedName = ((FieldError) objectError).getField();
			String message = ((FieldError) objectError).getDefaultMessage();
			map.put(filedName, message);

		}
		structure.setMessage("provide valid details");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(map);

		return new ResponseEntity<Object>(structure, HttpStatus.BAD_REQUEST);
	}
	
	
	
	@org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ResponseStructure<Object>> handleConstraintViolationException(ConstraintViolationException ex) {
		ResponseStructure<Object> structure = new ResponseStructure();
		Map<String, String> map = new HashMap<String, String>();

		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			String field = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			map.put(field, message);

		}

		structure.setMessage("provide proper details");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(map);

		return new ResponseEntity<ResponseStructure<Object>>(structure, HttpStatus.BAD_REQUEST);

	}
}
