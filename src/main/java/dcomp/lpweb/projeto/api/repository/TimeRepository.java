package dcomp.lpweb.projeto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dcomp.lpweb.projeto.api.model.Time;

@Repository
public interface TimeRepository extends JpaRepository<Time, Integer>{
}