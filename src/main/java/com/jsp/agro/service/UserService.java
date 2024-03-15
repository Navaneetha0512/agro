package com.jsp.agro.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.jsp.agro.dao.UserDao;
import com.jsp.agro.entity.User;
import com.jsp.agro.exception.EmailWrongException;
import com.jsp.agro.exception.IdNotFoundException;
import com.jsp.agro.exception.PasswordWrongException;
import com.jsp.agro.util.ResponseStructure;

@Service
public class UserService {

	@Autowired
	private UserDao dao;

	@Autowired
	private JavaMailSender mailsender;

	public ResponseEntity<ResponseStructure<User>> register(User user) {

		ResponseStructure<User> m = new ResponseStructure<User>();
		m.setData(dao.save(user));
		m.setMessage("user saved Succesfully");
		m.setStatus(HttpStatus.CREATED.value());
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("navaneethayanaki@gmail.com");
		message.setTo(user.getEmail());
		message.setSubject("Registration Mail");
		message.setText("Dear " + user.getFirstName() + "\n" + "\r\n"
				+ "Congratulations! Your account with Agro has been successfully created. Welcome to our platform!\r\n"
				+ "\r\n" + "Below are your account details:\r\n" + "\r\n" + "Email Address: " + user.getEmail() + "\r\n"
				+ "Password:" + user.getPassword());
		mailsender.send(message);
		return new ResponseEntity<ResponseStructure<User>>(m, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<Integer>> sendOTP(String email) {
		User db = dao.fetchByEmail(email);
		if (db!= null) {
			Random r = new Random(1000);
			int otp = r.nextInt(999999);
			ResponseStructure<Integer> m = new ResponseStructure<Integer>();
			m.setData(otp);
			m.setMessage("OTP sent Succesfully");
			m.setStatus(HttpStatus.FOUND.value());
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("navaneethayanaki@gmail.com");
			message.setTo(email);
			message.setSubject(" OTP verification");
			message.setText(" Your Password reset OTP is" + otp);
			mailsender.send(message);
			return new ResponseEntity<ResponseStructure<Integer>>(m, HttpStatus.FOUND);
		} 
		else
			throw new EmailWrongException();

	}

	public ResponseEntity<ResponseStructure<User>> update(User user) {

		User db = dao.fetchById(user.getId());
		if (db != null) {
			ResponseStructure<User> m = new ResponseStructure<User>();
			m.setData(dao.update(user));
			m.setMessage("user updated successfully");
			m.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(m, HttpStatus.FOUND);
		} else
			throw new IdNotFoundException();

	}

	public ResponseEntity<ResponseStructure<User>> delete(int id) {
		User db = dao.fetchById(id);
		if (db != null) {
			ResponseStructure<User> m = new ResponseStructure<User>();
			m.setData(dao.delete(id));
			m.setMessage("User deleted Succesfully");
			m.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(m, HttpStatus.FOUND);
		} else {
			throw new IdNotFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<User>> fetchById(int id) {
		User db = dao.fetchById(id);
		if (db != null) {
			ResponseStructure<User> m = new ResponseStructure<User>();
			m.setData(db);
			m.setMessage("User found Succesfully");
			m.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(m, HttpStatus.FOUND);
		} else {
			throw new IdNotFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<List<User>>> fetchall() {
		List<User> db = dao.fetchAll();
		if (db != null) {
			ResponseStructure<List<User>> m = new ResponseStructure<List<User>>();
			m.setData(db);
			m.setMessage("User found Succesfully");
			m.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<User>>>(m, HttpStatus.FOUND);
		} else {
			throw new IdNotFoundException();
		}
	}

//	public ResponseEntity<ResponseStructure<User>> fetchByEmail(String email) {
//		User db = dao.fetchByEmail(email);
//		if (db != null) {
//			ResponseStructure<User> m = new ResponseStructure<User>();
//			m.setData(db);
//			m.setMessage("User found Succesfully");
//			m.setStatus(HttpStatus.FOUND.value());
//			return new ResponseEntity<ResponseStructure<User>>(m, HttpStatus.FOUND);
//		} else {
//			throw new EmailWrongException();
//		}
//	}

	public ResponseEntity<ResponseStructure<User>> login(User user) {
		User db = dao.fetchByEmail(user.getEmail());
		if (db != null) {
			if (db.getPassword().equals(user.getPassword())) {
				ResponseStructure<User> structure = new ResponseStructure<User>();
				structure.setData(db);
				structure.setMessage("login Sucessfull");
				structure.setStatus(HttpStatus.FOUND.value());
				return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.FOUND);
			}
			throw new PasswordWrongException("Password incorrect");
		}
		throw new EmailWrongException("wrong email " + user.getEmail());
	}
}
