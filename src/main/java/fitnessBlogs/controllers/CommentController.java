package fitnessBlogs.controllers;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fitnessBlogs.entities.Comment;
import fitnessBlogs.repositories.CommentRepository;

@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveComment(@RequestBody Comment comment){
		try {
			commentRepository.save(new Comment(comment.getText(), comment.getCommenterId(), comment.getBlogId()));
			return new ResponseEntity<>("Comment saved.", HttpStatus.CREATED);
		}catch(Exception e) {
			if(e instanceof ConstraintViolationException) {
				throw e; // Catched by ConstrainViolationHandler
			}else {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<Comment>> getAllBlogs(@RequestParam String blogId){
		if(!ObjectId.isValid(blogId)) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); //Invalid hexSrting(ObjectId)
		Query query = new Query();
		query.addCriteria(Criteria.where("blogId").is(new ObjectId(blogId)));
		List<Comment> list =  mongoTemplate.find(query, Comment.class);
		System.out.println(list.toString());
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	public CommentController() {
		// TODO Auto-generated constructor stub
	}

}
