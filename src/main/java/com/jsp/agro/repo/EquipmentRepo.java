package com.jsp.agro.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.agro.entity.Equipments;
import com.jsp.agro.entity.User;

public interface EquipmentRepo extends JpaRepository<Equipments, Integer> {

	@Query("select a from Equipments  a where a.name=?1 ")
	 public List<Equipments> fetchByname(String name);

	@Query("select a from Equipments a where a.user=?1 ")
	public List<Equipments> fetchByUserid(User user);

}
