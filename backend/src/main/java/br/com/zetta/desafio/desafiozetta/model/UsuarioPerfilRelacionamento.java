package br.com.zetta.desafio.desafiozetta.model;


import javax.persistence.*;

@Entity
@Table(name = "usuario_perfis")
public class UsuarioPerfilRelacionamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(nullable = false, name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(nullable = false, name = "id_perfil")
    private Perfil perfil;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}
