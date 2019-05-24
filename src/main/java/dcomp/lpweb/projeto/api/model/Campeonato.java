package dcomp.lpweb.projeto.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "campeonato")
public class Campeonato {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Integer ano;
	
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
}