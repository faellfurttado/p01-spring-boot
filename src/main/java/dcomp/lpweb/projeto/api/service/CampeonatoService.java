package dcomp.lpweb.projeto.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dcomp.lpweb.projeto.api.model.Campeonato;
import dcomp.lpweb.projeto.api.repository.CampeonatoRepository;

@Service
public class CampeonatoService {
	
private final CampeonatoRepository campeonatoRepository;
	
	private final GenericoService<Campeonato> genericoService;
	
	@Autowired
	public CampeonatoService(CampeonatoRepository campeonatoRepository) {

	        this.campeonatoRepository = campeonatoRepository;
	        
	        genericoService = new GenericoService<>(campeonatoRepository);
	}
	
	@Transactional(readOnly = true)
    public List<Campeonato> todos() {
        return campeonatoRepository.findAll();
    }

    @Transactional
    public Campeonato salva(Campeonato campeonato) {
        return campeonatoRepository.save(campeonato);

    }

    @Transactional(readOnly = true)
    public Campeonato buscaPor(Integer id) {
        return campeonatoRepository.findById(id).get();

    }

    @Transactional
    public void excluiPor(Integer id) {
    	campeonatoRepository.deleteById(id);
    }

    @Transactional
    public Campeonato atualiza(Integer id, Campeonato campeonato) {

    	return  genericoService.atualiza(campeonato, id );
    }
    
    public List<Campeonato> buscaCategorias(List<Integer> idsCampeonatos) {
        return campeonatoRepository.findAllById(idsCampeonatos );
    }
}