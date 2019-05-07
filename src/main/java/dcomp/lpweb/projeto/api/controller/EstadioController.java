package dcomp.lpweb.projeto.api.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import dcomp.lpweb.projeto.api.controller.response.Resposta;
import dcomp.lpweb.projeto.api.model.Estadio;
import dcomp.lpweb.projeto.api.service.EstadioService;
import dcomp.lpweb.projeto.api.util.PropriedadesUtil;

@RestController
@RequestMapping("/estadios")
public class EstadioController {
	
	 private final EstadioService estadioService;

	    @Autowired
	    public EstadioController(EstadioService estadioService) {
	        this.estadioService = estadioService;
	    }

	    @GetMapping
	    public Resposta<List<EstadioDTO>> todos(){

	        List<Estadio> estadios = estadioService.todos();

	        List<EstadioDTO> estadiosDTO = new ArrayList<>(estadios.size());

	        estadios.forEach(estadio -> {
	                                  EstadioDTO estadioDTO = new EstadioDTO();
	                                  BeanUtils.copyProperties(estadio, estadioDTO);
	                                  estadiosDTO.add(estadioDTO );
	                           });

	        Resposta<List<EstadioDTO>> resposta = new Resposta<>();
	        resposta.setDados(estadiosDTO);

	        return resposta;
	    }
	    
	    @PostMapping
	    public ResponseEntity<Resposta<EstadioDTO>> salva(@RequestBody EstadioDTO estadioDTO ) {

	        Estadio estadio = new Estadio();
	        BeanUtils.copyProperties(estadioDTO, estadio);

	        Estadio estadioSalvo = estadioService.salva(estadio);

	        URI uri = ServletUriComponentsBuilder
	                .fromCurrentRequestUri()
	                .path("/{id}")
	                .buildAndExpand(estadioSalvo.getId())
	                .toUri();

	        BeanUtils.copyProperties(estadioSalvo, estadioDTO );

	        //TODO Refatorar este código, duplicado em outros métodos
	        Resposta<EstadioDTO> resposta = new Resposta<>();
	        resposta.setDados(estadioDTO);

	        return ResponseEntity.created(uri).body(resposta );
	    }
	    
	    @GetMapping("/{id}")
	    public Resposta<EstadioDTO> buscaPor(@PathVariable Integer id) {

	    	Estadio estadio = estadioService.buscaPor(id);
	    	EstadioDTO estadioDTO = new EstadioDTO();

	        BeanUtils.copyProperties(estadio, estadioDTO);

	        Resposta<EstadioDTO> resposta = new Resposta<>();
	        resposta.setDados(estadioDTO);

	        return resposta;
	    }

	    @DeleteMapping("/{id}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void exclui(@PathVariable Integer id) {
	    	estadioService.excluiPor(id );
	    }
	    
	    @PutMapping("/{id}")
	    public Resposta<EstadioDTO> altera(@PathVariable  Integer id, @RequestBody EstadioDTO estadioDTO) {


	    	Estadio estadio = estadioService.buscaPor(id);

	        BeanUtils.copyProperties(estadioDTO,
	        		estadio,
	                PropriedadesUtil.obterPropriedadesComNullDe(estadioDTO) );

	        Estadio categoriaAtualizada = estadioService.atualiza(id, estadio);
	        BeanUtils.copyProperties(categoriaAtualizada, estadioDTO );

	        Resposta<EstadioDTO> resposta = new Resposta<>();
	        resposta.setDados(estadioDTO);

	        return resposta;
	    }
}