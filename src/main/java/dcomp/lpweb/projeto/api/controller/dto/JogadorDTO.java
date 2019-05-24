package dcomp.lpweb.projeto.api.controller.dto;

import java.util.Calendar;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dcomp.lpweb.projeto.api.model.Jogador;
import dcomp.lpweb.projeto.api.model.Time;

public class JogadorDTO {
	
	private Integer id;
	
	@Size(min = 5, max = 100)
	private String nome;	
	
	@Size(min = 1, max = 20)
	private String genero;
	
	private Double altura;
	private boolean capitao;
	private Calendar nascimento;
	private Time timeEmQueJoga;
	
	private DTO<Jogador, JogadorDTO> dto = new DTO<>(this);

    public JogadorDTO() {  }

    public JogadorDTO(Jogador jogador) {
        this.comDadosDe(jogador );
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
	
	public boolean getEhCapitao(){
		return this.capitao;
	}
	
	public void setId(Integer id) {
        this.id = id;
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
	
	@JsonIgnore
    public Jogador getJogador() {
        return dto.getEntity(new Jogador() );
    }

    public JogadorDTO comDadosDe(Jogador jogador) {
        return dto.comDadosDe(jogador );
    }

    public Jogador atualizaIgnorandoNuloA(Jogador jogador) {
        return dto.mergeIgnorandoNulo(jogador );
    }
    
    @Override
    public String toString() {
        return "JogadorDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", genero='" + genero + '\'' +
                ", altura='" + altura + '\'' +
                ", capitao='" + capitao + '\'' +
                ", nascimento='" + nascimento + '\'' +
                ", timeEmQueJoga='" + timeEmQueJoga + '\'' +
                '}';
    }
}