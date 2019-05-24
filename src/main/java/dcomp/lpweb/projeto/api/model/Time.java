package dcomp.lpweb.projeto.api.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "time")
public class Time {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	@OneToOne
	@JoinColumn(name = "estadio_id")
	private Estadio sede;
	
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Time)) return false;
        Time time = (Time) o;
        return Objects.equals(getId(), time.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
    
    @Override
	 public String toString() {
	        return "Time{" +
	                "id=" + id +
	                ", nome='" + nome + '\'' +
	                ", estadio ='" + sede + '\'' +
	                '}';
	 }
}