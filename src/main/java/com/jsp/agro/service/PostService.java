package com.jsp.agro.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro.dao.PostDao;
import com.jsp.agro.dao.UserDao;
import com.jsp.agro.entity.Image;
import com.jsp.agro.entity.Post;
import com.jsp.agro.entity.User;
import com.jsp.agro.exception.IdNotFoundException;
import com.jsp.agro.exception.PostNotFoundException;
import com.jsp.agro.exception.UserNotFoundException;
import com.jsp.agro.util.ResponseStructure;

@Service
public class PostService {
	
	@Autowired
	private PostDao pdao;
	
	@Autowired
	private UserDao udao;
	
	
	ResponseStructure<Post> rs=new ResponseStructure<Post>();
	
	public ResponseEntity<ResponseStructure<Post>> savepost(int userid, MultipartFile file,String caption,String location) throws IOException{
			User udb = udao.fetchById(userid);
			if(udb!=null) {
				Image image=new Image();
				image.setName(file.getOriginalFilename());
				image.setData(file.getBytes());
				Post post=new Post();
				post.setImage(image);
				post.setCaption(caption);
				post.setLocation(location);
				post.setDate(LocalDateTime.now());
				pdao.savepost(post);
				List<Post> plist=new ArrayList<Post>();
				plist.add(post);
				plist.addAll(udb.getPost());
				udb.setPost(plist);
				udao.update(udb);
				rs.setMessage("post uploaded successfully");
				rs.setStatus(HttpStatus.ACCEPTED.value());
				rs.setData(post);
				return new ResponseEntity<ResponseStructure<Post>>(rs,HttpStatus.ACCEPTED);
			}else {
				throw new UserNotFoundException();
			}
			
	}
	
	public ResponseEntity<ResponseStructure<Post>> fetchPostById(int id){
		Post post=pdao.fetchById(id);
		if(post!=null) {
			rs.setMessage("Post fetched Successfully");
			rs.setStatus(HttpStatus.FOUND.value());
			rs.setData(post);
			return new ResponseEntity<ResponseStructure<Post>>(rs,HttpStatus.FOUND);
		}else {
			throw new PostNotFoundException("post id not found"+id);
		}
	}
	
//	public ResponseEntity<ResponseStructure<Post>> deletePostById(int id) throws IOException{
//		Post post = pdao.fetchById(id);
//		if(post!=null) {
//		List<User> users = udao.fetchAll();
//		 for(User u:users) {
//			 List<Post> posts = u.getPost();
//			 if(posts.contains(post)) {
//				 posts.remove(post);
//				 udao.update(u);
//				 pdao.deletePost(id);
//				 break;
//			 }
//			 
//		 }

}
