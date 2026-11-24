package br.edu.ceuma.apicursos.service;
import br.edu.ceuma.apicursos.dto.*;
import br.edu.ceuma.apicursos.model.Curso;
import br.edu.ceuma.apicursos.repository.CursoRepository;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.NoSuchElementException;
@Service
public class CursoService {
 private final CursoRepository repo;
 public CursoService(CursoRepository repo){this.repo=repo;}
 public Page<CursoResponse> listar(int page,int limit,String sort,BigDecimal min,BigDecimal max){
  int size=Math.max(1,Math.min(limit,100)); Sort order=Sort.by("asc".equalsIgnoreCase(sort)?Sort.Direction.ASC:Sort.Direction.DESC,"descricao");
  Specification<Curso> spec=Specification.where((root,q,cb)->cb.conjunction());
  if(min!=null) spec=spec.and((root,q,cb)->cb.greaterThanOrEqualTo(root.get("preco"),min));
  if(max!=null) spec=spec.and((root,q,cb)->cb.lessThanOrEqualTo(root.get("preco"),max));
  return repo.findAll(spec,PageRequest.of(Math.max(page,0),size,order)).map(CursoResponse::from);
 }
 public CursoResponse buscar(Long id){return CursoResponse.from(repo.findById(id).orElseThrow(()->new NoSuchElementException("Curso não encontrado")));}
 @Transactional public CursoResponse criar(CursoRequest dto){return CursoResponse.from(repo.save(new Curso(dto.descricao(),dto.turno(),dto.preco())));}
 @Transactional public CursoResponse atualizar(Long id,CursoRequest dto){Curso c=repo.findById(id).orElseThrow(()->new NoSuchElementException("Curso não encontrado"));c.setDescricao(dto.descricao());c.setTurno(dto.turno());c.setPreco(dto.preco());return CursoResponse.from(c);}
 @Transactional public void excluir(Long id){if(!repo.existsById(id))throw new NoSuchElementException("Curso não encontrado");repo.deleteById(id);}
}
