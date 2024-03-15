package com.jsp.agro.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.agro.entity.Image;
import com.jsp.agro.entity.User;

public interface UserRepo  extends JpaRepository<User, Integer>{

	@Query("select a from User a where a.email=?1")
	 public User fetchByemail(String email);

	@Query("select n from User n where n.image=?1")
	public User findByImage(Image image);

}
