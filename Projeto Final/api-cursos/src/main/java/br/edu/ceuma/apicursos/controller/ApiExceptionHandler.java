package br.edu.ceuma.apicursos.controller;
import org.springframework.http.*;import org.springframework.web.bind.MethodArgumentNotValidException;import org.springframework.web.bind.annotation.*;import org.springframework.http.converter.HttpMessageNotReadableException;import java.time.Instant;import java.util.*;
@RestControllerAdvice public class ApiExceptionHandler {
 private Map<String,Object> err(String code,String msg){return Map.of("timestamp",Instant.now().toString(),"error",code,"message",msg);}
 @ExceptionHandler(NoSuchElementException.class) ResponseEntity<?> notFound(NoSuchElementException e){return ResponseEntity.status(404).body(err("NOT_FOUND",e.getMessage()));}
 @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<?> invalid(MethodArgumentNotValidException e){var fields=e.getBindingResult().getFieldErrors().stream().map(x->Map.of("field",x.getField(),"message",Objects.toString(x.getDefaultMessage(),"inválido"))).toList();return ResponseEntity.badRequest().body(Map.of("timestamp",Instant.now().toString(),"error","VALIDATION_ERROR","fields",fields));}
 @ExceptionHandler(HttpMessageNotReadableException.class) ResponseEntity<?> malformed(){return ResponseEntity.badRequest().body(err("MALFORMED_JSON","Corpo JSON inválido"));}
 @ExceptionHandler(Exception.class) ResponseEntity<?> internal(Exception e){return ResponseEntity.internalServerError().body(err("INTERNAL_ERROR","Erro interno. Consulte o identificador com o suporte."));}
}
