package br.edu.ceuma.apicursos.model;

import jakarta.persistence.*;
@Entity @Table(name="usuarios")
public class Usuario {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false,unique=true) private String email;
    @Column(nullable=false) private String senhaHash;
    @Column(nullable=false) private String perfil;
    protected Usuario() {}
    public Usuario(String email,String senhaHash,String perfil){this.email=email;this.senhaHash=senhaHash;this.perfil=perfil;}
    public Long getId(){return id;} public String getEmail(){return email;} public String getSenhaHash(){return senhaHash;} public String getPerfil(){return perfil;}
}
