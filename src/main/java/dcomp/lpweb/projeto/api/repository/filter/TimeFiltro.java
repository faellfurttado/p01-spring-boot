package dcomp.lpweb.projeto.api.repository.filter;

import javax.validation.constraints.Size;

import dcomp.lpweb.projeto.api.model.Estadio;

public class TimeFiltro {
	
	private Integer id;
	private String nome;
	private Estadio sede;
	
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
	
	 @Override
	 public String toString() {
	        return "TimeDTO{" +
	                "id=" + id +
	                ", nome='" + nome + '\'' +
	                ", estadio ='" + sede + '\'' +
	                '}';
	 }
}