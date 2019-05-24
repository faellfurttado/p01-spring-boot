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

import dcomp.lpweb.projeto.api.controller.dto.PartidaDTO;
import dcomp.lpweb.projeto.api.controller.event.HeaderLocationEvento;
import dcomp.lpweb.projeto.api.controller.response.Erro;
import dcomp.lpweb.projeto.api.controller.response.Resposta;
import dcomp.lpweb.projeto.api.controller.validation.Validacao;
import dcomp.lpweb.projeto.api.model.Partida;
import dcomp.lpweb.projeto.api.service.PartidaService;

@RestController
@RequestMapping("/partidas")
public class PartidaController {
	
	private final PartidaService partidaService;
	 
	 @Autowired
	 private ApplicationEventPublisher publisher;

	    @Autowired
	    public PartidaController(PartidaService partidaService) {
	        this.partidaService = partidaService;
	    }

	    @GetMapping
	    public Resposta<List<PartidaDTO>> todos(){
	    	
	    	List<PartidaDTO> partidaesDTO = partidaService.todos()
                .stream()
                .map(partida -> new PartidaDTO(partida))
                .collect(Collectors.toList());


	    	 return Resposta.comDadosDe(partidaesDTO);
	    }
	    
	    @PostMapping
	    public ResponseEntity<Resposta<PartidaDTO>> salva(@Valid @RequestBody PartidaDTO partidaDTO,
	    		                                          HttpServletResponse response) {

	    	Partida partidaSalvo = partidaService.salva(partidaDTO.getPartida());

	        publisher.publishEvent(new HeaderLocationEvento(this, response, partidaSalvo.getId()) );

	        return ResponseEntity.status(HttpStatus.CREATED).body(Resposta.comDadosDe(new PartidaDTO(partidaSalvo)));
	    }
	    
	    @GetMapping("/{id}")
	    public Resposta<PartidaDTO> buscaPor(@PathVariable Integer id) {

	    	Partida partida = partidaService.buscaPor(id);
	        return Resposta.comDadosDe(new PartidaDTO(partida ));
	    }

	    @DeleteMapping("/{id}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void exclui(@PathVariable Integer id) {
	    	partidaService.excluiPor(id );
	    }
	    
	    @PutMapping("/{id}")
	    public ResponseEntity<Resposta<PartidaDTO>> altera(@PathVariable  Integer id, @RequestBody PartidaDTO partidaDTO) {


	    	Partida partida = partidaService.buscaPor(id);

	    	List<Erro> erros = this.getErros(new PartidaDTO(partida) );
	        if (existe(erros)) {
	            return ResponseEntity.badRequest().body(Resposta.com(erros) );
	        }

	        Partida partidaAtualizado = partidaService.atualiza(id, partida);
	        return ResponseEntity.ok(Resposta.comDadosDe(new PartidaDTO(partidaAtualizado )));
	    }
	    
	    private boolean existe(List<Erro> erros) {
	        return Objects.nonNull( erros ) &&  !erros.isEmpty();
	    }

	    private List<Erro> getErros(PartidaDTO dto) {
	        Validacao<PartidaDTO> validacao = new Validacao<>();
	        return validacao.valida(dto);
	    }
}