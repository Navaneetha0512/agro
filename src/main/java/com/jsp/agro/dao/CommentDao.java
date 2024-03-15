package com.jsp.agro.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro.entity.Comment;
import com.jsp.agro.repo.CommentRepo;

@Repository
public class CommentDao {

	@Autowired
	private  CommentRepo repo;
	
	public Comment saveComment(Comment comnt) {
		 return repo.save(comnt);
	}
	
	public Comment deleteComment(int id) {
		Optional<Comment> db = repo.findById(id);
		if(db.isPresent()) {
			repo.deleteById(id);
			return db.get();
		}else {
			return null;
		}
	}
	
	public Comment fetchById(int id) {
		Optional<Comment> db = repo.findById(id);
		return db.get();
	}
}
