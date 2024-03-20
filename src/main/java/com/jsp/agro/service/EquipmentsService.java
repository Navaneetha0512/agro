package com.jsp.agro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.agro.dao.EquipmentsDao;
import com.jsp.agro.dao.UserDao;
import com.jsp.agro.entity.Equipments;
import com.jsp.agro.entity.User;
import com.jsp.agro.exception.EquipmentNotFoundException;
import com.jsp.agro.exception.IdNotFoundException;
import com.jsp.agro.exception.UserNotFoundException;
import com.jsp.agro.util.ResponseStructure;

@Service
public class EquipmentsService {

	@Autowired
	private EquipmentsDao edao;

	@Autowired
	private UserDao udao;

	ResponseStructure<Equipments> m = new ResponseStructure<Equipments>();

	public ResponseEntity<ResponseStructure<Equipments>> save(int userid, Equipments e) {
		User userdb = udao.fetchById(userid);
		if (userdb != null) {
			e.setUser(userdb);
			m.setData(edao.saveEquipments(e));
			m.setMessage("Equipments uploaded succesfully");
			m.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Equipments>>(m, HttpStatus.CREATED);
		} else {
			throw new UserNotFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<Equipments>> update(Equipments e) {

		Equipments db = edao.fetchById(e.getId());
		if (db != null) {
			m.setData(edao.updateEquipments(e));
			m.setMessage("Equipments updated successfully");
			m.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Equipments>>(m, HttpStatus.FOUND);
		} else
			throw new EquipmentNotFoundException();

	}

	public ResponseEntity<ResponseStructure<Equipments>> delete(int eId) {
		Equipments db = edao.fetchById(eId);

		if (db != null) {
			m.setData(edao.updateEquipments(db));
			List<Equipments> listdb = edao.fetchAll();
			for (Equipments l : listdb) {
				if (l == db) {
					edao.deleteEquipments(eId);
					break;
				}

			}

			m.setMessage("Equipments deleted successfully");
			m.setStatus(HttpStatus.FOUND.value());

			return new ResponseEntity<ResponseStructure<Equipments>>(m, HttpStatus.FOUND);

		} else
			throw new EquipmentNotFoundException();

	}

	public ResponseEntity<ResponseStructure<Equipments>> fetchById(int id) {
		Equipments db = edao.fetchById(id);
		if (db != null) {
			m.setData(db);
			m.setMessage("data fetched successfully");
			m.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Equipments>>(m, HttpStatus.FOUND);
		} else
			throw new EquipmentNotFoundException();
	}

	public ResponseEntity<ResponseStructure<List<Equipments>>> fetchByName(String name) {
		List<Equipments> db = edao.fetchByName(name);
		if (db != null) {
			ResponseStructure<List<Equipments>> r = new ResponseStructure<List<Equipments>>();
			r.setData(db);
			r.setMessage("data fetched successfully");
			r.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Equipments>>>(r, HttpStatus.FOUND);
		} else
			throw new EquipmentNotFoundException();
	}

	public ResponseEntity<ResponseStructure<List<Equipments>>> fetchAll() {
		List<Equipments> db = edao.fetchAll();
		if (db != null) {
			ResponseStructure<List<Equipments>> r = new ResponseStructure<List<Equipments>>();
			r.setData(db);
			r.setMessage("data fetched successfully");
			r.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Equipments>>>(r, HttpStatus.FOUND);
		} else
			throw new EquipmentNotFoundException();
	}

	public ResponseEntity<ResponseStructure<List<Equipments>>> fetchByUserid(int id) {
		User db = udao.fetchById(id);

		if (db != null) {
			List<Equipments> listdb = edao.fetchByUserid(db);

			ResponseStructure<List<Equipments>> r = new ResponseStructure<List<Equipments>>();
			r.setData(listdb);
			r.setMessage("data fetched successfully");
			r.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Equipments>>>(r, HttpStatus.FOUND);
		} else
			throw new EquipmentNotFoundException();
	}

}
