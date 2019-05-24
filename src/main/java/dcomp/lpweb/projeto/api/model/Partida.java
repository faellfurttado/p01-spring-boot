package dcomp.lpweb.projeto.api.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "partida")
public class Partida {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	private Calendar data;
	
	private Integer pontuacaoMandante;
	private Integer pontuacaoVisitante;
	
	@ManyToOne
	@JoinColumn(name = "time_mandante_id")
	private Time mandante;
	
	@ManyToOne
	@JoinColumn(name = "time_visitante_id")
	private Time visitante;
	
	@ManyToOne
	@JoinColumn(name = "campeonato_id")
	private Campeonato campeonato;
	
	public Integer getId() {
		return this.id;
	}
	
	public Integer getPontuacaoMandante() {
		return this.pontuacaoMandante;
	}
	
	public Integer getPontuacaoVisitante() {
		return this.pontuacaoVisitante;
	}
	
	public Calendar getData() {
		return this.data;
	}
	
	public Time getMandante(){
		return this.mandante;
	}
	
	public Time getVisitante(){
		return this.visitante;
	}
	
	public Campeonato getCampeonato(){
		return this.campeonato;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setData(Calendar data) {
		this.data = data;
	}
	
	public void setPontuacaoMandante(Integer pontuacaoMandante) {
		this.pontuacaoMandante = pontuacaoMandante;
	}
	
	public void setPontuacaoVisitante(Integer pontuacaoVisitante) {
		this.pontuacaoVisitante = pontuacaoVisitante;
	}
	
	public void setMandante(Time mandante){
		this.mandante = mandante;
	}
	
	public void setVisitante(Time visitante){
		this.visitante = visitante;
	}
	
	public void setCampeonato(Campeonato campeonato){
		this.campeonato = campeonato;
	}
}