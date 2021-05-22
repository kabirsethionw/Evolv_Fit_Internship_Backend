package fitnessBlogs.controllers;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fitnessBlogs.entities.User;
import fitnessBlogs.entities.User.accountStatus;
import fitnessBlogs.repositories.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/save")
	public ResponseEntity<User> createUser(@RequestBody User user){
		try {
			User newUser = userRepository.save(new User(user.getName(), user.getEmail(), Enum.valueOf(accountStatus.class, "ACTIVATED")));
			return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		}catch(Exception e) {
			
			if(e instanceof ConstraintViolationException) {
				throw e; // Catched by ConstrainViolationHandler
			}else {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}			
		}
	}
	
	@GetMapping("/get")
	public ResponseEntity<User> getUser(@RequestParam String userId){
		try {
			if(!ObjectId.isValid(userId)) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); //Invalid hexSrting(ObjectId)
			Optional<User> userData = userRepository.findById(userId);
			if(userData.isPresent()) {
				return new ResponseEntity<>(userData.get(), HttpStatus.OK);
			}else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
