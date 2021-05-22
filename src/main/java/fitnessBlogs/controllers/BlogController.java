package fitnessBlogs.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fitnessBlogs.entities.Blog;
import fitnessBlogs.entities.User;
import fitnessBlogs.repositories.BlogRepository;
import fitnessBlogs.repositories.UserRepository;
import fitnessBlogs.util.BlogId;

@RestController
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	private BlogRepository blogRepository; 
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/save")
	public ResponseEntity<Blog> saveBlog(@RequestBody Blog content, @RequestParam String userId) throws Exception{
		try {
			if(!ObjectId.isValid(userId)) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); //Invalid hexSrting(ObjectId)
			User trueUser;
			Optional<User> user = userRepository.findById(userId);
			
			if(user.isPresent()) {
				trueUser = user.get();
				//User found, now we create and save blog.
				Blog newBlog = blogRepository.save(new Blog(content.getTitle(), content.getText()));
				trueUser.addBlog(new BlogId(new ObjectId(newBlog.getId())));
				User test = userRepository.save(trueUser); //Update
				System.out.println(test.getBlogs().toString());
				return new ResponseEntity<>(newBlog, HttpStatus.CREATED);
			}else {
				throw new Exception("Unable to fetch user data.");
			}	
		}catch(Exception e) {
			if(e instanceof ConstraintViolationException) {
				throw e; // Catched by ConstrainViolationHandler
			}else {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Blog>> getAllBlogs(){
		try {
			List<Blog> blogList = blogRepository.findAll(Sort.by(Sort.Direction.ASC, "createdAt")); //Latest blogs first
			return new ResponseEntity<>(blogList, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getById")
	public ResponseEntity<Blog> getBlog(@RequestParam String blogId){
		try {
			
			Optional<Blog> blog = blogRepository.findById(blogId);
			if(!ObjectId.isValid(blogId)) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); //Invalid hexSrting(ObjectId)
			if(blog.isPresent()) {
				return new ResponseEntity<>(blog.get(), HttpStatus.OK);
			}else {
				throw new Exception("Blog not found.");
			}
		}catch(Exception e) {
			System.out.println(e.getClass().descriptorString());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteBlog(@RequestParam String blogId){
			try {
				if(!ObjectId.isValid(blogId)) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); //Invalid hexSrting(ObjectId)
				ObjectId bid = new ObjectId(new String(blogId)); 
				Query query = new Query().addCriteria(Criteria.where("blogs").elemMatch(Criteria.where("_blogId").is(bid)));
				User user = mongoTemplate.findOne(query, User.class);
				Optional<Blog> deletedBlog = blogRepository.findById(blogId);
				if(deletedBlog.isPresent()) {
					blogRepository.delete(deletedBlog.get());
					user.removeBlog(new BlogId(new ObjectId(blogId))); // not removing
					userRepository.save(user);
					return new ResponseEntity<>("Blog "+deletedBlog.get().getTitle()+" is deleted.", HttpStatus.OK);
				}else {
					// No user Found
					return new ResponseEntity<>("Blog not found", HttpStatus.NOT_FOUND);
				}
			}catch(Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateBlog(@RequestParam String blogId, @RequestBody Blog updates){
		try {
			if(!ObjectId.isValid(blogId)) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); //Invalid hexSrting(ObjectId)
			Optional<Blog> oldBlog = blogRepository.findById(blogId);
			if(oldBlog.isPresent()) {
				oldBlog.get().setText(updates.getText());
				oldBlog.get().setTitle(updates.getTitle());
				oldBlog.get().setModifiedAt(new Date());
				blogRepository.save(oldBlog.get());
				return new ResponseEntity<>("Blog updated successfully.", HttpStatus.CREATED);
			}else {
				return new ResponseEntity<>("Blog not found", HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e) {
			if(e instanceof ConstraintViolationException) {
				throw e; // Catched by ConstrainViolationHandler
			}else {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	
	public BlogController() {
		// TODO Auto-generated constructor stub
		
	}
	
}


