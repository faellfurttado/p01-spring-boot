package dcomp.lpweb.projeto.api.controller.dto;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dcomp.lpweb.projeto.api.model.Estadio;
import dcomp.lpweb.projeto.api.model.Time;

public class TimeDTO {
	
	private Integer id;
	
	@Size(min = 3, max = 100)
	private String nome;

	private Estadio sede;
	
	private DTO<Time, TimeDTO> dto = new DTO<>(this);

    public TimeDTO() {  }

    public TimeDTO(Time time) {
        this.comDadosDe(time );
    }
    
    public Integer getId(){
		return this.id;
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public Estadio getEstadio(){
		return this.sede;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setEstadio(Estadio estadio) {
		this.sede = estadio;
	}
    
    @JsonIgnore
    public Time getTime() {
        return dto.getEntity(new Time() );
    }

    public TimeDTO comDadosDe(Time time) {
        return dto.comDadosDe(time );
    }

    public Time atualizaIgnorandoNuloA(Time time) {
        return dto.mergeIgnorandoNulo(time );
    }
    
    @Override
    public String toString() {
        return "TimeDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", estadio ='" + sede + '\'' +
                '}';
    }
}