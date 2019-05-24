package dcomp.lpweb.projeto.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "time")
public class Time {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	@OneToOne
	@JoinColumn(name = "estadio_sede_id")
	private Estadio sede;
	
	@OneToOne
	@JoinColumn(name = "capitao_id")
	private Jogador capitao;
	
	public Time() {}

    public Time(String nome, Estadio sede) {
        this.nome = nome;
        this.sede = sede;
    }
	
	public Integer getId(){
		return this.id;
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	/*
	 * private Partida partidasComoVisitante;
	 * private Partida partidasComoMandante;
	 * private Campeonato campeonatos;
	 * */
}