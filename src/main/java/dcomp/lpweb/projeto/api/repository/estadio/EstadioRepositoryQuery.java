package dcomp.lpweb.projeto.api.repository.estadio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dcomp.lpweb.projeto.api.model.Estadio;
import dcomp.lpweb.projeto.api.repository.filter.EstadioFiltro;

public interface EstadioRepositoryQuery {

	Page<Estadio> filtrar(EstadioFiltro filtro, Pageable pageable);
}