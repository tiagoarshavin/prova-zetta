package br.com.zetta.desafio.desafiozetta.repository;

import br.com.zetta.desafio.desafiozetta.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
}
