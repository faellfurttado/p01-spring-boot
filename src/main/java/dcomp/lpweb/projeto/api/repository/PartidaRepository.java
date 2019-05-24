package dcomp.lpweb.projeto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dcomp.lpweb.projeto.api.model.Partida;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Integer>{
}