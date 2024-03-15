package com.jsp.agro.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.agro.entity.Post;

public interface PostRepo extends JpaRepository<Post, Integer> {

	@Query("select a from Post a where comments=?1")
	 Post fetchPostByComment(int id);

}
