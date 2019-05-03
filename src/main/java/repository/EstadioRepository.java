package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Estadio;

@Repository
public interface EstadioRepository extends JpaRepository<Estadio, Integer> {
}
