package br.com.zetta.desafio.desafiozetta.repository;

import br.com.zetta.desafio.desafiozetta.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
