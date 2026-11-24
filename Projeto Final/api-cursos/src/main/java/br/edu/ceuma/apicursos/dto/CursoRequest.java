package br.edu.ceuma.apicursos.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record CursoRequest(@NotBlank @Size(max=140) String descricao, @NotBlank @Size(max=40) String turno, @NotNull @DecimalMin("0.00") BigDecimal preco) {}
