package com.jsp.agro.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro.entity.Address;
import com.jsp.agro.repo.AdressRepo;

@Repository
public class AddressDao {

	@Autowired
	private AdressRepo repo;

	public Address updateAddress(Address address) {

		Optional<Address> data = repo.findById(address.getId());
		
		if(data.isPresent()) {
			Address db = data.get();
			if(address.getHouseNum()==null) {
				address.setHouseNum(db.getHouseNum());
			}
			if(address.getStreet()==null) {
				address.setStreet(db.getStreet());
			}
			if(address.getLandmark()==null) {
				address.setLandmark(db.getLandmark());
			}
			if(address.getMandal()==null) {
				address.setMandal(db.getMandal());
			}
			if(address.getDistrict()==null) {
				address.setDistrict(db.getDistrict());
			}
			if(address.getState()==null) {
				address.setState(db.getState());
			}
			if(address.getPincode()==0) {
				address.setPincode(db.getPincode());
			}
		}
		return null;

	}

}
