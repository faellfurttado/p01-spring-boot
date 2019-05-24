package dcomp.lpweb.projeto.api.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estadio")
public class Estadio {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;	
	private String endereco;
	
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
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estadio)) return false;
        Estadio estadio = (Estadio) o;
        return Objects.equals(getId(), estadio.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
    
    @Override
    public String toString() {
        return "Estadio{" +
                "nome='" + nome + '\'' +
                ", endereco=" + endereco +
                ", estadioId=" + id +
                '}';
    }
}