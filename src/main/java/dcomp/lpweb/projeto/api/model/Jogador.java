package dcomp.lpweb.projeto.api.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "jogador")
public class Jogador {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	@Temporal(TemporalType.DATE)
	private Calendar nascimento;
	
	private String genero;
	
	private Double altura;
	
	@ManyToOne
	@JoinColumn(name = "time_id")
	private Time timeEmQueJoga;
	
	/*
	@OneToOne
	@JoinColumn(name = "capitao_time_id")
	private Time timeQueCapitaneia;
	*/
}