package com.jsp.agro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.agro.entity.Equipments;
import com.jsp.agro.service.EquipmentsService;
import com.jsp.agro.util.ResponseStructure;

@RestController
public class EquipmentsController {

	@Autowired
	private EquipmentsService service;

	@PostMapping("/saveEquipment")
	public ResponseEntity<ResponseStructure<Equipments>> save(@RequestParam int userid,@RequestBody Equipments e) {
		return service.save(userid, e);

	}

	@PutMapping("/updateEquipment")
	public ResponseEntity<ResponseStructure<Equipments>> update(@RequestBody Equipments e) {
		return service.update( e);
	}

	@DeleteMapping("/deleteEquipment")
	public ResponseEntity<ResponseStructure<Equipments>> delete(@RequestParam int eId) {
		return service.delete(eId);

	}

	@GetMapping("/fetchEquipmentById")
	public ResponseEntity<ResponseStructure<Equipments>> fetchById(@RequestParam int id) {
		return service.fetchById(id);

	}

	@GetMapping("/fetchEquipmentByName")
	public ResponseEntity<ResponseStructure<List<Equipments>>> fetchByName(@RequestParam String name) {
		return service.fetchByName(name);

	}

	@GetMapping("/fetchEquipments")
	public ResponseEntity<ResponseStructure<List<Equipments>>> fetchAll() {
		return service.fetchAll();

	}

	@GetMapping("/fetchEquipmentByUserId")
	public ResponseEntity<ResponseStructure<List<Equipments>>> fetchByUserid(@RequestParam int id) {
		return service.fetchByUserid(id);

	}

}
