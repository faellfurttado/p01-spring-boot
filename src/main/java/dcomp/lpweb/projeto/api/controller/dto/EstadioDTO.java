package dcomp.lpweb.projeto.api.controller.dto;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dcomp.lpweb.projeto.api.model.Estadio;

public class EstadioDTO {
	
	private Integer id;
	
	@Size(min = 5, max = 100)
	private String nome;	
	
	@Size(min = 5, max = 100)
	private String endereco;
	
	private DTO<Estadio, EstadioDTO> dto = new DTO<>(this);

    public EstadioDTO() {  }

    public EstadioDTO(Estadio estadio) {
        this.comDadosDe(estadio );
    }
	
	public Integer getId(){
		return this.id;
	}
	
	public void setId(Integer id) {
        this.id = id;
    }
	
	public String getNome(){
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEndereco(){
		return this.endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	@JsonIgnore
    public Estadio getEstadio() {
        return dto.getEntity(new Estadio() );
    }

    public EstadioDTO comDadosDe(Estadio estadio) {
        return dto.comDadosDe(estadio );
    }

    public Estadio atualizaIgnorandoNuloA(Estadio estadio) {
        return dto.mergeIgnorandoNulo(estadio );
    }
    
    @Override
    public String toString() {
        return "EstadioDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}