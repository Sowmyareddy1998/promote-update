package ls.lesm.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RoleAreadyExistException.class)
	public ResponseEntity<?> roleExistHandler(RoleAreadyExistException roleExist, WebRequest webRequest){
		
		ApiErorrs apiErorrs =new ApiErorrs(new Date(),roleExist.getErrorMessage(),roleExist.getErrorCode());
		
		return new ResponseEntity<>(apiErorrs, HttpStatus.ALREADY_REPORTED); 
		
	}
	
	@ExceptionHandler(DuplicateEntryException.class)
	public ResponseEntity<?> duplicateEntryHandler(DuplicateEntryException ex, WebRequest webRequest){
		ApiErorrs apiErorrs =new ApiErorrs(new Date(),ex.getErrorMessage(), ex.getErrorCode());
		return new ResponseEntity<>(apiErorrs, HttpStatus.ALREADY_REPORTED); 
		
	}

}
