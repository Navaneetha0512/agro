package com.jsp.agro.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro.dao.ImageDao;
import com.jsp.agro.dao.UserDao;
import com.jsp.agro.entity.Image;
import com.jsp.agro.entity.User;
import com.jsp.agro.exception.ImageNotFoundException;
import com.jsp.agro.exception.UserNotFoundException;
import com.jsp.agro.util.ResponseStructure;

@Service
public class ImageService {

	@Autowired
	ImageDao idao;

	@Autowired
	UserDao udao;

	ResponseStructure<Image> rs = new ResponseStructure<Image>();

//	public ResponseEntity<ResponseStructure<Image>> uploadImage(int id, MultipartFile file) throws IOException {
//		User user = udao.fetchById(id);
//		if (user != null) {
//			Image image = new Image();
//			image.setName(file.getOriginalFilename());
//			image.setData(file.getBytes());
//			idao.uploadImage(image);
//			user.setImage(image);
//			udao.update(user);
//			rs.setMessage("Image uploaded successfully");
//			rs.setStatus(HttpStatus.ACCEPTED.value());
//			rs.setData(image);
//			return new ResponseEntity<ResponseStructure<Image>>(rs, HttpStatus.ACCEPTED);
//		} else {
//			throw new UserNotFoundException("User not found with the Id: " + id);
//		}
//	}
//	public ResponseEntity<ResponseStructure<Image>> updateImage(int id, MultipartFile file) throws IOException{
//		Image image=dao.fetchImageById(id);
//		if(image!=null) {
//		image.setId(id);
//	image.setName(file.getOriginalFilename());
//		image.setData(file.getBytes());
//		dao.updateImage(image);
//		rs.setMessage("Image uploaded successfully");
//		rs.setStatus(HttpStatus.ACCEPTED.value());
//		rs.setData(image);
//		return new ResponseEntity<ResponseStructure<Image>>(rs,HttpStatus.ACCEPTED);
//		}
//		else
//			throw new ImageNotFoundException();
//	}

	public ResponseEntity<byte[]> fetchImageById(int id) {
		Image image = idao.fetchImageById(id);
		if (image != null) {
			byte[] imageBytes = idao.fetchImageById(id).getData();

			// Set appropriate content type (e.g., image/jpeg)
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
		} else {
			throw new ImageNotFoundException("Image not found with id: " + id);
		}
	}

	public ResponseEntity<ResponseStructure<Image>> updateImage(int id, MultipartFile file) throws IOException {
		User udb = udao.fetchById(id);
		if (udb != null) {
			if (udb.getImage() != null) {
				Image img = udb.getImage();
				img.setName(file.getOriginalFilename());
				img.setData(file.getBytes());
				idao.updateImage(img);
				rs.setData(img);
				rs.setMessage("Image updated successfully");
				rs.setStatus(HttpStatus.ACCEPTED.value());
			} else {
				Image i = new Image();
				i.setName(file.getOriginalFilename());
				i.setData(file.getBytes());
				idao.uploadImage(i);
				udb.setImage(i);
				udao.update(udb);
				rs.setData(i);
				rs.setMessage("Image uploaded successfully");
				rs.setStatus(HttpStatus.ACCEPTED.value());
			}

			return new ResponseEntity<ResponseStructure<Image>>(rs, HttpStatus.ACCEPTED);
		} else {
			throw new UserNotFoundException();
		}
	}

//	public ResponseEntity<ResponseStructure<Image>> fetchImageById(int id) {
//		Image image = idao.fetchImageById(id);
//		if (image != null) {
//			rs.setMessage("Image fetched successfully");
//			rs.setStatus(HttpStatus.FOUND.value());
//			rs.setData(image);
//			return new ResponseEntity<ResponseStructure<Image>>(rs, HttpStatus.FOUND);
//		} else {
//			throw new ImageNotFoundException("Image not found with id: " + id);
//		}
//	}

	public ResponseEntity<ResponseStructure<String>> deleteImageById(int id) {
		ResponseStructure<String> rss = new ResponseStructure<String>();
		Image image = idao.fetchImageById(id);
		if (image != null) {
			User img = udao.findByImage(image);
			if (img != null) {
				img.setImage(null);
				udao.update(img);
			}
			idao.deleteImage(image);
			rss.setMessage("Image deleted successfully");
			rss.setStatus(HttpStatus.FOUND.value());
			rss.setData(image.getName() + " Deleted Successfully");
			return new ResponseEntity<ResponseStructure<String>>(rss, HttpStatus.FOUND);
		} else {
			throw new ImageNotFoundException("Image not found with id: " + id);
		}
	}
}
