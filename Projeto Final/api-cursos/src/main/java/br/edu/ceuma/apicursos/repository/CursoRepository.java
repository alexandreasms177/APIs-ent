package br.edu.ceuma.apicursos.repository;
import br.edu.ceuma.apicursos.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
public interface CursoRepository extends JpaRepository<Curso,Long>, JpaSpecificationExecutor<Curso> {}
