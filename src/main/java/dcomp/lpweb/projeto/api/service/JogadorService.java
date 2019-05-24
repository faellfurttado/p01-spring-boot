package dcomp.lpweb.projeto.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import dcomp.lpweb.projeto.api.model.Jogador;
import dcomp.lpweb.projeto.api.repository.JogadorRepository;

public class JogadorService {
private final JogadorRepository jogadorRepository;
	
	private final GenericoService<Jogador> genericoService;
	
	@Autowired
	public JogadorService(JogadorRepository jogadorRepository) {

	        this.jogadorRepository = jogadorRepository;
	        
	        genericoService = new GenericoService<>(jogadorRepository);
	}
	
	@Transactional(readOnly = true)
    public List<Jogador> todos() {
        return jogadorRepository.findAll();
    }

    @Transactional
    public Jogador salva(Jogador jogador) {
        return jogadorRepository.save(jogador);

    }

    @Transactional(readOnly = true)
    public Jogador buscaPor(Integer id) {
        return jogadorRepository.findById(id).get();

    }

    @Transactional
    public void excluiPor(Integer id) {
    	jogadorRepository.deleteById(id);
    }

    @Transactional
    public Jogador atualiza(Integer id, Jogador jogador) {

    	return  genericoService.atualiza(jogador, id );
    }
    
    public List<Jogador> buscaCategorias(List<Integer> idsJogadores) {
        return jogadorRepository.findAllById(idsJogadores );
    }
}