package br.gov.sp.fatec.projetomaven.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "pro_professor")
@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pro_id")
    private Long id;
    
    @Column(name="pro_nome_usuario")
    private String nomeUsuario;
    
    @Column(name="pro_senha")
    private String senha;

    @Column(name="pro_titulo")
    private String titulo;

    @OneToMany(fetch=FetchType.LAZY,mappedBy = "avaliador")
    private Set<Trabalho> trabalhoAvaliados;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Set<Trabalho> getTrabalhoAvaliados() {
        return trabalhoAvaliados;
    }

    public void setTrabalhoAvaliados(Set<Trabalho> trabalhoAvaliados) {
        this.trabalhoAvaliados = trabalhoAvaliados;
    }
}