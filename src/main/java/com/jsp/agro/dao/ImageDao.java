package com.jsp.agro.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro.entity.Image;
import com.jsp.agro.repo.ImageRepo;

@Repository
public class ImageDao {

	@Autowired
	private ImageRepo repo;
	
	public Image uploadImage(Image image){
		return repo.save(image);
	}
	
	
	public Image updateImage(Image image){
		Optional<Image> db = repo.findById(image.getId());
		if(db.isPresent()) {
			Image data = db.get();
			if(image.getName()!=null) {
				data.setName(image.getName());
			}
			if(image.getData()!=null) {
				image.setData(image.getData());
			}
			repo.save(image);
		}
		return null;
		
	}
	
	
	public Image fetchImageById(int id){
		Optional<Image> image = repo.findById(id);
		if(image.isEmpty()) {
			return null;
		}
		else {
			return image.get();
		}
	}
	
	
	public void deleteImage(Image image){
		repo.delete(image);
	}


}
