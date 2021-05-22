package fitnessBlogs.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ConstrainViolationHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value= {javax.validation.ConstraintViolationException.class})
	public ResponseEntity<Object> handleConflict(Exception e, WebRequest wr){
		System.out.println("Caught: " + e.getClass().getName());
		ConstraintViolationException cve = (ConstraintViolationException)e;
		return new ResponseEntity<>(cve.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	public ConstrainViolationHandler() {
		// TODO Auto-generated constructor stub
		System.out.println("Here");
	}

}
