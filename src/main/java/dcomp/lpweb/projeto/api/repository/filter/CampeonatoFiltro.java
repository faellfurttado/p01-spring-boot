package dcomp.lpweb.projeto.api.repository.filter;

public class CampeonatoFiltro {
	
private Integer id;
	
	private String nome;
	
	private Integer ano;
    
    public Integer getId() {
		return this.id;
	}
	
	public Integer getAno() {
		return this.ano;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	 
	 @Override
	 public String toString() {
	        return "TimeDTO{" +
	                "id=" + id +
	                ", nome='" + nome + '\'' +
	                ", ano ='" + ano + '\'' +
	                '}';
	 }
}