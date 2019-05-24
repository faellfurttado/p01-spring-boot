package dcomp.lpweb.projeto.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dcomp.lpweb.projeto.api.model.Partida;
import dcomp.lpweb.projeto.api.repository.PartidaRepository;

@Service
public class PartidaService {
	
private final PartidaRepository partidaRepository;
	
	private final GenericoService<Partida> genericoService;
	
	@Autowired
	public PartidaService(PartidaRepository partidaRepository) {

	        this.partidaRepository = partidaRepository;
	        
	        genericoService = new GenericoService<>(partidaRepository);
	}
	
	@Transactional(readOnly = true)
    public List<Partida> todos() {
        return partidaRepository.findAll();
    }

    @Transactional
    public Partida salva(Partida partida) {
        return partidaRepository.save(partida);

    }

    @Transactional(readOnly = true)
    public Partida buscaPor(Integer id) {
        return partidaRepository.findById(id).get();

    }

    @Transactional
    public void excluiPor(Integer id) {
    	partidaRepository.deleteById(id);
    }

    @Transactional
    public Partida atualiza(Integer id, Partida partida) {

    	return  genericoService.atualiza(partida, id );
    }
    
    public List<Partida> buscaCategorias(List<Integer> idsPartidas) {
        return partidaRepository.findAllById(idsPartidas );
    }
}