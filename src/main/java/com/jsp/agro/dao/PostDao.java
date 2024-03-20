
package com.jsp.agro.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro.entity.Post;
import com.jsp.agro.repo.PostRepo;

@Repository
public class PostDao {
	
	@Autowired
	private PostRepo repo;
	
	public Post savepost(Post post) {
		return repo.save(post);
		
	}
	
	public Post fetchById(int id) {
		Optional<Post> db = repo.findById(id);
		if(db.isPresent())
			return db.get();
		else
			return null;
		
	}
	
	public Post updatePost(Post post) {
		Optional<Post> db = repo.findById(post.getId());
		if(db.isPresent()) {
			Post p = db.get();
			if(post.getImage()==null) {
				post.setImage(p.getImage());
			}
			if(post.getCaption()==null) {
				post.setCaption(p.getCaption());
			}
			if(post.getComments()==null) {
				post.setComments(p.getComments());
			}
			if(post.getDate()==null) {
				post.setDate(p.getDate());
			}if(post.getLocation()==null) {
				post.setLocation(p.getLocation());
			}
			return repo.save(post);
		}
		return null;
	}
	
	public Post deletePost(int id) {
		Optional<Post> db = repo.findById(id);
		if(db.isPresent()) {
			repo.deleteById(id);
			return db.get();
		}
		else
			return null;
	}
	
	public Post fetchPostByComment(int id) {
		return repo.fetchPostByComment(id);
	}

	public List<Post> fetchAll() {
		
		return repo.findAll();
	}
}
