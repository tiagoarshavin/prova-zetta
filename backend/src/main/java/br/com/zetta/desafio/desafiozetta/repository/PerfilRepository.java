package br.com.zetta.desafio.desafiozetta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.zetta.desafio.desafiozetta.model.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
