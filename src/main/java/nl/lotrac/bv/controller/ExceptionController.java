package nl.lotrac.bv.controller;

import nl.lotrac.bv.exceptions.*;
import nl.lotrac.bv.model.MessageFrontEnd;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExceptionController {

    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exception(RecordNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> exception(BadRequestException exception) {
        return ResponseEntity.badRequest().build();
    }




    @ExceptionHandler(value = NameExistsException.class)
    public ResponseEntity<MessageFrontEnd> exception(NameExistsException exception){
        return new ResponseEntity<>(new MessageFrontEnd(exception.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = NameNotFoundException.class)
    public ResponseEntity<MessageFrontEnd> exception(NameNotFoundException exception){
        return new ResponseEntity<>(new MessageFrontEnd(exception.getMessage()), HttpStatus.UNAUTHORIZED);
    }



}
