package dcomp.lpweb.projeto.api.repository.filter;

public class EstadioFiltro {
	
	private Integer estadioId;
	private String nome;
	private String endereco;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getEstadioId() {
        return estadioId;
    }

    public void setEstadioId(Integer estadioId) {
        this.estadioId = estadioId;
    }

    @Override
    public String toString() {
        return "Esatadiofiltro{" +
                "nome='" + nome + '\'' +
                ", endereco=" + endereco +
                ", estadioId=" + estadioId +
                '}';
    }
}