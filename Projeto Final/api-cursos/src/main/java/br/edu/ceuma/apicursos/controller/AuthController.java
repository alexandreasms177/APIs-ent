package br.edu.ceuma.apicursos.controller;
import br.edu.ceuma.apicursos.config.JwtService;import br.edu.ceuma.apicursos.model.Usuario;import br.edu.ceuma.apicursos.repository.UsuarioRepository;import jakarta.validation.Valid;import jakarta.validation.constraints.*;import org.springframework.http.*;import org.springframework.security.crypto.password.PasswordEncoder;import org.springframework.web.bind.annotation.*;import java.util.*;
@RestController @RequestMapping("/api/auth") public class AuthController {
 public record Credenciais(@Email @NotBlank String email,@NotBlank @Size(min=8) String senha){} public record Token(String token,String perfil){}
 private final UsuarioRepository users;private final PasswordEncoder encoder;private final JwtService jwt;
 public AuthController(UsuarioRepository users,PasswordEncoder encoder,JwtService jwt){this.users=users;this.encoder=encoder;this.jwt=jwt;}
 @PostMapping("/register") public ResponseEntity<?> register(@Valid @RequestBody Credenciais c){if(users.findByEmail(c.email()).isPresent())return ResponseEntity.status(409).body(Map.of("error","E-mail já cadastrado"));Usuario u=users.save(new Usuario(c.email(),encoder.encode(c.senha()),"USER"));return ResponseEntity.status(201).body(Map.of("id",u.getId(),"email",u.getEmail(),"perfil",u.getPerfil()));}
 @PostMapping("/login") public ResponseEntity<?> login(@Valid @RequestBody Credenciais c){var user=users.findByEmail(c.email());if(user.isEmpty()||!encoder.matches(c.senha(),user.get().getSenhaHash()))return ResponseEntity.status(401).body(Map.of("error","Credenciais inválidas"));return ResponseEntity.ok(new Token(jwt.emitir(c.email(),user.get().getPerfil()),user.get().getPerfil()));}
}
