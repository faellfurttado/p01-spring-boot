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
	private String genero;
	private Double altura;
	
	@JoinColumn(name = "capitao")
	private boolean capitao;
	
	@Temporal(TemporalType.DATE)
	private Calendar nascimento;
	
	@ManyToOne
	@JoinColumn(name = "time_id")
	private Time timeEmQueJoga;
	
	public Jogador() {
	
	}
	
	public Jogador(String nome,String genero, Double altura, boolean capitao, Calendar nascimento, Time timeEmQueJoga) {
		
		this.nome = nome;
		this.genero = genero;
		this.altura = altura;
		this.capitao = capitao;
		this.nascimento = nascimento;
		this.timeEmQueJoga = timeEmQueJoga;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public String getGenero(){
		return this.genero;
	}
	
	public Double getAltura(){
		return this.altura;
	}
	
	public Calendar getNascimento(){
		return this.nascimento;
	}
	
	public Time getTimeEmQueJoga(){
		return this.timeEmQueJoga;
	}
	
	
	public void setNascimento(Calendar nascimento){
		this.nascimento = nascimento;
	}
	
	public void setCapitao(boolean valor){
		this.capitao = valor;
	}
	
	public boolean getEhCapitao(){
		return this.capitao;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setAltura(Double altura){
		this.altura = altura;
	}
	
	public void setTimeEmQueJoga(Time timeEmQueJoga){
		this.timeEmQueJoga = timeEmQueJoga;
	}
}