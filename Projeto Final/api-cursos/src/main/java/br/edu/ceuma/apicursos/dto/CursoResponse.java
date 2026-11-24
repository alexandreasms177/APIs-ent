package br.edu.ceuma.apicursos.dto;
import br.edu.ceuma.apicursos.model.Curso;
import java.math.BigDecimal;
public record CursoResponse(Long id,String descricao,String turno,BigDecimal preco) { public static CursoResponse from(Curso c){return new CursoResponse(c.getId(),c.getDescricao(),c.getTurno(),c.getPreco());} }
