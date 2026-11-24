package br.edu.ceuma.apicursos.controller;
import org.springframework.beans.factory.annotation.Value;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/desafio") public class DesafioController {
 @Value("${app.student.name}") String nome; @Value("${app.student.ra}") String ra;
 @GetMapping("/ra") public String ra(){return "O RA do aluno "+nome+" é "+ra;}
}
