package dcomp.lpweb.projeto.api.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dcomp.lpweb.projeto.api.controller.dto.TimeDTO;
import dcomp.lpweb.projeto.api.controller.event.HeaderLocationEvento;
import dcomp.lpweb.projeto.api.controller.response.Erro;
import dcomp.lpweb.projeto.api.controller.response.Resposta;
import dcomp.lpweb.projeto.api.controller.validation.Validacao;
import dcomp.lpweb.projeto.api.model.Time;
import dcomp.lpweb.projeto.api.service.TimeService;

@RestController
@RequestMapping("/times")
public class TimeController {
	
	private final TimeService timeService;
	 
	 @Autowired
	 private ApplicationEventPublisher publisher;

	    @Autowired
	    public TimeController(TimeService timeService) {
	        this.timeService = timeService;
	    }

	    @GetMapping
	    public Resposta<List<TimeDTO>> todos(){
	    	
	    	List<TimeDTO> timeesDTO = timeService.todos()
                  .stream()
                  .map(time -> new TimeDTO(time))
                  .collect(Collectors.toList());


	    	 return Resposta.comDadosDe(timeesDTO);
	    }
	    
	    @PostMapping
	    public ResponseEntity<Resposta<TimeDTO>> salva(@Valid @RequestBody TimeDTO timeDTO,
	    		                                          HttpServletResponse response) {

	    	Time timeSalvo = timeService.salva(timeDTO.getTime());

	        publisher.publishEvent(new HeaderLocationEvento(this, response, timeSalvo.getId()) );

	        return ResponseEntity.status(HttpStatus.CREATED).body(Resposta.comDadosDe(new TimeDTO(timeSalvo)));
	    }
	    
	    @GetMapping("/{id}")
	    public Resposta<TimeDTO> buscaPor(@PathVariable Integer id) {

	    	Time time = timeService.buscaPor(id);
	        return Resposta.comDadosDe(new TimeDTO(time ));
	    }

	    @DeleteMapping("/{id}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void exclui(@PathVariable Integer id) {
	    	timeService.excluiPor(id );
	    }
	    
	    @PutMapping("/{id}")
	    public ResponseEntity<Resposta<TimeDTO>> altera(@PathVariable  Integer id, @RequestBody TimeDTO timeDTO) {


	    	Time time = timeService.buscaPor(id);

	    	List<Erro> erros = this.getErros(new TimeDTO(time) );
	        if (existe(erros)) {
	            return ResponseEntity.badRequest().body(Resposta.com(erros) );
	        }

	        Time timeAtualizado = timeService.atualiza(id, time);
	        return ResponseEntity.ok(Resposta.comDadosDe(new TimeDTO(timeAtualizado )));
	    }
	    
	    private boolean existe(List<Erro> erros) {
	        return Objects.nonNull( erros ) &&  !erros.isEmpty();
	    }

	    private List<Erro> getErros(TimeDTO dto) {
	        Validacao<TimeDTO> validacao = new Validacao<>();
	        return validacao.valida(dto);
	    }
}