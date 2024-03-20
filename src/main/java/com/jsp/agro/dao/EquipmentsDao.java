package com.jsp.agro.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro.entity.Equipments;
import com.jsp.agro.entity.User;
import com.jsp.agro.repo.EquipmentRepo;

@Repository
public class EquipmentsDao {

	@Autowired
	private EquipmentRepo repo;
	
	public Equipments saveEquipments(Equipments e) {
		return repo.save(e);
	}
	
	public Equipments updateEquipments(Equipments e) {
		Optional<Equipments> db = repo.findById(e.getId());
		if(db.isPresent()) {
			Equipments data = db.get();
			if(e.getName()==null) {
				e.setName(data.getName());
			}
			if(e.getCostPerHour()==0) {
				e.setCostPerHour(data.getCostPerHour());
			}
			if(e.getQuantity()==0) {
				e.setQuantity(data.getQuantity());
			}
			if(e.getUser()==null) {
				e.setUser(data.getUser());
			}
			return repo.save(e);
		}
		else
			return null;
	}
	
	
	public Equipments deleteEquipments(int id) {
		Optional<Equipments> db = repo.findById(id);
		if(db.isPresent()) {
			repo.delete(db.get());
			return db.get();
		}
		else
			return null;
	}
	
	public Equipments fetchById(int id) {
		return repo.findById(id).get();
	}
	
	public List<Equipments> fetchByName(String name){
		return repo.fetchByname(name);
	}
	
	public List<Equipments> fetchByUserid(User user){
		return repo.fetchByUserid(user);
	}
	
	public List<Equipments> fetchAll(){
		return repo.findAll();
	}
}
