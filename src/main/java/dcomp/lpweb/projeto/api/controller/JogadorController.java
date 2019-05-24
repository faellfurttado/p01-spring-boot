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

import dcomp.lpweb.projeto.api.controller.dto.JogadorDTO;
import dcomp.lpweb.projeto.api.controller.event.HeaderLocationEvento;
import dcomp.lpweb.projeto.api.controller.response.Erro;
import dcomp.lpweb.projeto.api.controller.response.Resposta;
import dcomp.lpweb.projeto.api.controller.validation.Validacao;
import dcomp.lpweb.projeto.api.model.Jogador;
import dcomp.lpweb.projeto.api.service.JogadorService;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {
	
	private final JogadorService jogadorService;
	 
	 @Autowired
	 private ApplicationEventPublisher publisher;

	    @Autowired
	    public JogadorController(JogadorService jogadorService) {
	        this.jogadorService = jogadorService;
	    }

	    @GetMapping
	    public Resposta<List<JogadorDTO>> todos(){
	    	
	    	List<JogadorDTO> jogadoresDTO = jogadorService.todos()
                   .stream()
                   .map(jogador -> new JogadorDTO(jogador))
                   .collect(Collectors.toList());


	    	 return Resposta.comDadosDe(jogadoresDTO);
	    }
	    
	    @PostMapping
	    public ResponseEntity<Resposta<JogadorDTO>> salva(@Valid @RequestBody JogadorDTO jogadorDTO,
	    		                                          HttpServletResponse response) {

	    	Jogador jogadorSalvo = jogadorService.salva(jogadorDTO.getJogador());

	        publisher.publishEvent(new HeaderLocationEvento(this, response, jogadorSalvo.getId()) );

	        return ResponseEntity.status(HttpStatus.CREATED).body(Resposta.comDadosDe(new JogadorDTO(jogadorSalvo)));
	    }
	    
	    @GetMapping("/{id}")
	    public Resposta<JogadorDTO> buscaPor(@PathVariable Integer id) {

	    	Jogador jogador = jogadorService.buscaPor(id);
	        return Resposta.comDadosDe(new JogadorDTO(jogador ));
	    }

	    @DeleteMapping("/{id}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void exclui(@PathVariable Integer id) {
	    	jogadorService.excluiPor(id );
	    }
	    
	    @PutMapping("/{id}")
	    public ResponseEntity<Resposta<JogadorDTO>> altera(@PathVariable  Integer id, @RequestBody JogadorDTO jogadorDTO) {


	    	Jogador jogador = jogadorService.buscaPor(id);

	    	List<Erro> erros = this.getErros(new JogadorDTO(jogador) );
	        if (existe(erros)) {
	            return ResponseEntity.badRequest().body(Resposta.com(erros) );
	        }

	        Jogador jogadorAtualizado = jogadorService.atualiza(id, jogador);
	        return ResponseEntity.ok(Resposta.comDadosDe(new JogadorDTO(jogadorAtualizado )));
	    }
	    
	    private boolean existe(List<Erro> erros) {
	        return Objects.nonNull( erros ) &&  !erros.isEmpty();
	    }

	    private List<Erro> getErros(JogadorDTO dto) {
	        Validacao<JogadorDTO> validacao = new Validacao<>();
	        return validacao.valida(dto);
	    }
}