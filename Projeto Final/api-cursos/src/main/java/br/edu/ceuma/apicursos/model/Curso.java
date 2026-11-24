package br.edu.ceuma.apicursos.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cursos")
public class Curso {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, length = 140) private String descricao;
    @Column(nullable = false, length = 40) private String turno;
    @Column(nullable = false, precision = 10, scale = 2) private BigDecimal preco;
    public Curso() {}
    public Curso(String descricao, String turno, BigDecimal preco) { this.descricao=descricao; this.turno=turno; this.preco=preco; }
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getDescricao(){return descricao;} public void setDescricao(String descricao){this.descricao=descricao;}
    public String getTurno(){return turno;} public void setTurno(String turno){this.turno=turno;}
    public BigDecimal getPreco(){return preco;} public void setPreco(BigDecimal preco){this.preco=preco;}
}
