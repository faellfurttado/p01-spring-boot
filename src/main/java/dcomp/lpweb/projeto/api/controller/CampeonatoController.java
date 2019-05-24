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

import dcomp.lpweb.projeto.api.controller.dto.CampeonatoDTO;
import dcomp.lpweb.projeto.api.controller.event.HeaderLocationEvento;
import dcomp.lpweb.projeto.api.controller.response.Erro;
import dcomp.lpweb.projeto.api.controller.response.Resposta;
import dcomp.lpweb.projeto.api.controller.validation.Validacao;
import dcomp.lpweb.projeto.api.model.Campeonato;
import dcomp.lpweb.projeto.api.service.CampeonatoService;

@RestController
@RequestMapping("/campeonatos")
public class CampeonatoController {
	
	private final CampeonatoService campeonatoService;
	 
	 @Autowired
	 private ApplicationEventPublisher publisher;

	    @Autowired
	    public CampeonatoController(CampeonatoService campeonatoService) {
	        this.campeonatoService = campeonatoService;
	    }

	    @GetMapping
	    public Resposta<List<CampeonatoDTO>> todos(){
	    	
	    	List<CampeonatoDTO> campeonatoesDTO = campeonatoService.todos()
                 .stream()
                 .map(campeonato -> new CampeonatoDTO(campeonato))
                 .collect(Collectors.toList());


	    	 return Resposta.comDadosDe(campeonatoesDTO);
	    }
	    
	    @PostMapping
	    public ResponseEntity<Resposta<CampeonatoDTO>> salva(@Valid @RequestBody CampeonatoDTO campeonatoDTO,
	    		                                          HttpServletResponse response) {

	    	Campeonato campeonatoSalvo = campeonatoService.salva(campeonatoDTO.getCampeonato());

	        publisher.publishEvent(new HeaderLocationEvento(this, response, campeonatoSalvo.getId()) );

	        return ResponseEntity.status(HttpStatus.CREATED).body(Resposta.comDadosDe(new CampeonatoDTO(campeonatoSalvo)));
	    }
	    
	    @GetMapping("/{id}")
	    public Resposta<CampeonatoDTO> buscaPor(@PathVariable Integer id) {

	    	Campeonato campeonato = campeonatoService.buscaPor(id);
	        return Resposta.comDadosDe(new CampeonatoDTO(campeonato ));
	    }

	    @DeleteMapping("/{id}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void exclui(@PathVariable Integer id) {
	    	campeonatoService.excluiPor(id );
	    }
	    
	    @PutMapping("/{id}")
	    public ResponseEntity<Resposta<CampeonatoDTO>> altera(@PathVariable  Integer id, @RequestBody CampeonatoDTO campeonatoDTO) {


	    	Campeonato campeonato = campeonatoService.buscaPor(id);

	    	List<Erro> erros = this.getErros(new CampeonatoDTO(campeonato) );
	        if (existe(erros)) {
	            return ResponseEntity.badRequest().body(Resposta.com(erros) );
	        }

	        Campeonato campeonatoAtualizado = campeonatoService.atualiza(id, campeonato);
	        return ResponseEntity.ok(Resposta.comDadosDe(new CampeonatoDTO(campeonatoAtualizado )));
	    }
	    
	    private boolean existe(List<Erro> erros) {
	        return Objects.nonNull( erros ) &&  !erros.isEmpty();
	    }

	    private List<Erro> getErros(CampeonatoDTO dto) {
	        Validacao<CampeonatoDTO> validacao = new Validacao<>();
	        return validacao.valida(dto);
	    }	
}