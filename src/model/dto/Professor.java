package model.dto;

public class Professor implements IEntity{
	private Integer id;
	private String cpf;
	private String nome;
	private Float qtdPontos;
	private Integer idAreaConhecimento;
	
	public Professor(Integer id, String cpf, String nome, Float qtdPontos, Integer idAreaConhecimento) {
		this.id= id;
		this.cpf= cpf;
		this.nome=nome;
		this.qtdPontos= qtdPontos;
		this.idAreaConhecimento= idAreaConhecimento;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Float getQtdPontos() {
		return qtdPontos;
	}
	public void setQtdPontos(Float qtdPontos) {
		this.qtdPontos = qtdPontos;
	}
	
	public Integer getIdAreaConhecimento() {
		return idAreaConhecimento;
	}
	public void setIdAreaConhecimento(Integer idAreaConhecimento) {
		this.idAreaConhecimento = idAreaConhecimento;
	}
	@Override
	public String toString() {
		return "Professor [cpf=" + cpf + ", nome=" + nome + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
        if (!(obj instanceof Professor)) return false;
        Professor other = (Professor) obj;
        return this.id.equals(other.id);
	}
	@Override
	public int hashCode() {
		if (id!=null) {
			return id;
		}else {
			return 0;
		}
	}
	
}
