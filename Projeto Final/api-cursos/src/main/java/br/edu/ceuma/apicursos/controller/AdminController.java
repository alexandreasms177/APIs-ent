package br.edu.ceuma.apicursos.controller;
import br.edu.ceuma.apicursos.model.Usuario;import br.edu.ceuma.apicursos.repository.UsuarioRepository;import org.springframework.security.access.prepost.PreAuthorize;import org.springframework.web.bind.annotation.*;import java.util.*;
@RestController @RequestMapping("/api/admin") public class AdminController {
 private final UsuarioRepository users;public AdminController(UsuarioRepository users){this.users=users;}
 @GetMapping("/usuarios") @PreAuthorize("hasRole('ADMIN')") public List<Map<String,Object>> usuarios(){return users.findAll().stream().map(u->Map.<String,Object>of("id",u.getId(),"email",u.getEmail(),"perfil",u.getPerfil())).toList();}
}
