package dcomp.lpweb.projeto.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dcomp.lpweb.projeto.api.model.Time;
import dcomp.lpweb.projeto.api.repository.TimeRepository;

@Service
public class TimeService {
private final TimeRepository timeRepository;
	
	private final GenericoService<Time> genericoService;
	
	@Autowired
	public TimeService(TimeRepository timeRepository) {

	        this.timeRepository = timeRepository;
	        
	        genericoService = new GenericoService<>(timeRepository);
	}
	
	@Transactional(readOnly = true)
    public List<Time> todos() {
        return timeRepository.findAll();
    }

    @Transactional
    public Time salva(Time time) {
        return timeRepository.save(time);

    }

    @Transactional(readOnly = true)
    public Time buscaPor(Integer id) {
        return timeRepository.findById(id).get();

    }

    @Transactional
    public void excluiPor(Integer id) {
    	timeRepository.deleteById(id);
    }

    @Transactional
    public Time atualiza(Integer id, Time time) {

    	return  genericoService.atualiza(time, id );
    }
    
    public List<Time> buscaCategorias(List<Integer> idsTimees) {
        return timeRepository.findAllById(idsTimees );
    }
}