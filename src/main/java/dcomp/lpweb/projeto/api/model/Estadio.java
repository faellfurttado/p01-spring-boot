package dcomp.lpweb.projeto.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "estadio")
public class Estadio {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;	
	private String endereco;
	
	//@OneToOne
	//@JoinColumn(name = "time_id")
	//private Time time;
	
	public Estadio() {}

    public Estadio(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
      
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
	
	public String getEndereco(){
		return this.endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}