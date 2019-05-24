package dcomp.lpweb.projeto.api.controller.dto;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dcomp.lpweb.projeto.api.model.Campeonato;

public class CampeonatoDTO {
	
	private Integer id;
	
	@Size(min = 5, max = 100)
	private String nome;
	
	private Integer ano;
	
	private DTO<Campeonato, CampeonatoDTO> dto = new DTO<>(this);

    public CampeonatoDTO() {  }

    public CampeonatoDTO(Campeonato campeonato) {
        this.comDadosDe(campeonato );
    }
    
    public Integer getId() {
		return this.id;
	}
	
	public Integer getAno() {
		return this.ano;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	 @JsonIgnore
	    public Campeonato getCampeonato() {
	        return dto.getEntity(new Campeonato() );
	 }

	 public CampeonatoDTO comDadosDe(Campeonato campeonato) {
	        return dto.comDadosDe(campeonato );
	 }

	 public Campeonato atualizaIgnorandoNuloA(Campeonato campeonato) {
	        return dto.mergeIgnorandoNulo(campeonato );
	 }
	 
	 @Override
	 public String toString() {
	        return "CampeonatoDTO{" +
	                "id=" + id +
	                ", nome='" + nome + '\'' +
	                ", ano ='" + ano + '\'' +
	                '}';
	 }
}