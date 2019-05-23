package dcomp.lpweb.projeto.api.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.HttpStatus;

import dcomp.lpweb.projeto.api.controller.dto.EstadioDTO;
import dcomp.lpweb.projeto.api.controller.event.Validacao;
import dcomp.lpweb.projeto.api.controller.response.Erro;
import dcomp.lpweb.projeto.api.controller.response.Resposta;
import dcomp.lpweb.projeto.api.controller.validation.HeaderLocationEvento;
import dcomp.lpweb.projeto.api.model.Estadio;
import dcomp.lpweb.projeto.api.service.EstadioService;
import dcomp.lpweb.projeto.api.util.PropriedadesUtil;

@RestController
@RequestMapping("/estadios")
public class EstadioController {
	
	 private final EstadioService estadioService;
	 
	 @Autowired
	 private ApplicationEventPublisher publisher;

	    @Autowired
	    public EstadioController(EstadioService estadioService) {
	        this.estadioService = estadioService;
	    }

	    @GetMapping
	    public Resposta<List<EstadioDTO>> todos(){
	    	
	    	List<EstadioDTO> estadiosDTO = estadioService.todos()
                    .stream()
                    .map(estadio -> new EstadioDTO(estadio))
                    .collect(Collectors.toList());


	    	 return Resposta.comDadosDe(estadiosDTO);
	    }
	    
	    @PostMapping
	    public ResponseEntity<Resposta<EstadioDTO>> salva(@Valid @RequestBody EstadioDTO estadioDTO,
	    		                                          HttpServletResponse response) {

	    	Estadio estadioSalvo = estadioService.salva(estadioDTO.getEstadio());

	        publisher.publishEvent(new HeaderLocationEvento(this, response, estadioSalvo.getId()) );

	        return ResponseEntity.status(HttpStatus.CREATED)
	                             .body(Resposta.comDadosDe(new EstadioDTO(estadioSalvo)));
	    }
	    
	    @GetMapping("/{id}")
	    public Resposta<EstadioDTO> buscaPor(@PathVariable Integer id) {

	    	Estadio estadio = estadioService.buscaPor(id);
	        return Resposta.comDadosDe(new EstadioDTO(estadio ));
	    }

	    @DeleteMapping("/{id}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void exclui(@PathVariable Integer id) {
	    	estadioService.excluiPor(id );
	    }
	    
	    @PutMapping("/{id}")
	    public ResponseEntity<Resposta<EstadioDTO>> altera(@PathVariable  Integer id, @RequestBody EstadioDTO estadioDTO) {


	    	Estadio estadio = estadioService.buscaPor(id);

	    	List<Erro> erros = this.getErros(new EstadioDTO(estadio) );
	        if (existe(erros)) {
	            return ResponseEntity.badRequest().body(Resposta.com(erros) );
	        }

	        Estadio estadioAtualizado = estadioService.atualiza(id, estadio);
	        return ResponseEntity.ok(Resposta.comDadosDe(new EstadioDTO(estadioAtualizado )));
	    }
	    
	    private boolean existe(List<Erro> erros) {
	        return Objects.nonNull( erros ) &&  !erros.isEmpty();
	    }

	    private List<Erro> getErros(EstadioDTO dto) {
	        Validacao<EstadioDTO> validacao = new Validacao<>();
	        return validacao.valida(dto);
	    }
}