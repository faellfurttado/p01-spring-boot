package dcomp.lpweb.projeto.api.repository.filter;

import java.util.Calendar;
import dcomp.lpweb.projeto.api.model.Time;

public class JogadorFiltro {
	
	private Integer jogadorId;
	private String nome;
	private String genero;
	private Double altura;
	private boolean capitao;
	private Calendar nascimento;
	private Time timeEmQueJoga;
	
	public Integer getId(){
		return this.jogadorId;
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
	
	public boolean getEhCapitao(){
		return this.capitao;
	}
	
	public void setId(Integer jogadorId) {
        this.jogadorId = jogadorId;
    }
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setNascimento(Calendar nascimento){
		this.nascimento = nascimento;
	}
	
	public void setCapitao(boolean valor){
		this.capitao = valor;
	}
	
	public void setAltura(Double altura){
		this.altura = altura;
	}
	
	public void setTimeEmQueJoga(Time timeEmQueJoga){
		this.timeEmQueJoga = timeEmQueJoga;
	}
	
	@Override
    public String toString() {
        return "JogadorDTO{" +
                "jogadorId=" + jogadorId +
                ", nome='" + nome + '\'' +
                ", genero='" + genero + '\'' +
                ", altura='" + altura + '\'' +
                ", capitao='" + capitao + '\'' +
                ", nascimento='" + nascimento + '\'' +
                ", timeEmQueJoga='" + timeEmQueJoga + '\'' +
                '}';
    }
}