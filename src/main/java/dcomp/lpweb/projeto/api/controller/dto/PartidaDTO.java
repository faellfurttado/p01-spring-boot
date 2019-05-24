package dcomp.lpweb.projeto.api.controller.dto;

import java.util.Calendar;
import com.fasterxml.jackson.annotation.JsonIgnore;

import dcomp.lpweb.projeto.api.model.Campeonato;
import dcomp.lpweb.projeto.api.model.Partida;
import dcomp.lpweb.projeto.api.model.Time;

public class PartidaDTO {
	
	private Integer id;
	private Calendar data;
	private Integer pontuacaoMandante;
	private Integer pontuacaoVisitante;
	private Time mandante;
	private Time visitante;
	private Campeonato campeonato;
	
	private DTO<Partida, PartidaDTO> dto = new DTO<>(this);

    public PartidaDTO() {  }

    public PartidaDTO(Partida partida) {
        this.comDadosDe(partida );
    }
    
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
    
	 @JsonIgnore
	    public Partida getPartida() {
	        return dto.getEntity(new Partida() );
	 }

	 public PartidaDTO comDadosDe(Partida partida) {
	        return dto.comDadosDe(partida );
	 }

	 public Partida atualizaIgnorandoNuloA(Partida partida) {
	        return dto.mergeIgnorandoNulo(partida );
	 }
	 
	 @Override
	 public String toString() {
	        return "PartidaDTO{" +
	                "id=" + id +
	                ", data='" + data + '\'' +
	                ", pontuacaoMandante ='" + pontuacaoMandante + '\'' +
	                ", pontuacaoVisitante='" + pontuacaoMandante + '\'' +
	                ", mandante='" + mandante + '\'' +
	                ", visitante='" + visitante + '\'' +
	                ", campeonato='" + campeonato + '\'' +
	                '}';
	 }
}