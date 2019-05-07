package dcomp.lpweb.projeto.api.service;

import dcomp.lpweb.projeto.api.model.Estadio;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dcomp.lpweb.projeto.api.repository.EstadioRepository;

@Service
public class EstadioService {
	
	private final EstadioRepository estadioRepository;
	
	@Autowired
	public EstadioService(EstadioRepository estadioRepository) {

	        this.estadioRepository = estadioRepository;
	}
	
	@Transactional(readOnly = true)
    public List<Estadio> todos() {
        return estadioRepository.findAll();
    }

    @Transactional
    public Estadio salva(Estadio estadio) {
        return estadioRepository.save(estadio);

    }

    @Transactional(readOnly = true)
    public Estadio buscaPor(Integer id) {
        return estadioRepository.findById(id).get();

    }

    @Transactional
    public void excluiPor(Integer id) {
    	estadioRepository.deleteById(id);
    }

    @Transactional
    public Estadio atualiza(Integer id, Estadio estadio) {

    	Estadio estadioSalvo = this.buscaPor(id);
        BeanUtils.copyProperties(estadio, estadioSalvo, "id");

        return  estadioSalvo;
    }
}