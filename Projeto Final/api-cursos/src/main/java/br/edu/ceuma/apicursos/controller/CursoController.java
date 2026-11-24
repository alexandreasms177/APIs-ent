package br.edu.ceuma.apicursos.controller;
import br.edu.ceuma.apicursos.dto.*;import br.edu.ceuma.apicursos.service.CursoService;
import jakarta.validation.Valid;import org.springframework.data.domain.Page;import org.springframework.http.*;import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;import java.util.*;
@RestController @RequestMapping("/api/cursos")
public class CursoController {
 private final CursoService service; public CursoController(CursoService service){this.service=service;}
 @GetMapping public Map<String,Object> listar(@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="20") int limit,@RequestParam(defaultValue="asc") String sort,@RequestParam(required=false) BigDecimal minPrice,@RequestParam(required=false) BigDecimal maxPrice){Page<CursoResponse> p=service.listar(page,limit,sort,minPrice,maxPrice);return Map.of("data",p.getContent(),"meta",Map.of("page",p.getNumber(),"limit",p.getSize(),"totalItems",p.getTotalElements(),"totalPages",p.getTotalPages(),"hasNext",p.hasNext(),"hasPrevious",p.hasPrevious()),"links",Map.of("self","/api/cursos?page="+page+"&limit="+limit,"next",p.hasNext()?"/api/cursos?page="+(page+1)+"&limit="+limit:"","previous",p.hasPrevious()?"/api/cursos?page="+(page-1)+"&limit="+limit:""));}
 @GetMapping("/{id}") public CursoResponse buscar(@PathVariable Long id){return service.buscar(id);}
 @PostMapping public ResponseEntity<CursoResponse> criar(@Valid @RequestBody CursoRequest request){return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(request));}
 @PutMapping("/{id}") public ResponseEntity<Void> atualizar(@PathVariable Long id,@Valid @RequestBody CursoRequest request){service.atualizar(id,request);return ResponseEntity.noContent().build();}
 @DeleteMapping("/{id}") public ResponseEntity<Void> excluir(@PathVariable Long id){service.excluir(id);return ResponseEntity.noContent().build();}
}
