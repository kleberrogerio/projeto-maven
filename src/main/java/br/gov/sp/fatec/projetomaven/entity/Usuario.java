package br.gov.sp.fatec.projetomaven.entity;

@Table(name)
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="alu_id")
    private Long id;
    
    @Column(name="alu_nome_usuario",unique = true,length = 50, nullable = false)
    private String nomeUsuario;
    
    @Column(name="alu_senha",length = 50,nullable = false)
    private String senha;

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


    
}