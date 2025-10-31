package model.dto;

public class Curso {
	private Integer id;
	private String nome;
	private String areaConhecimento;
	
	public Curso(Integer id,String nome, String areaConhecimento) {
		this.id= id;
		this.nome= nome;
		this.areaConhecimento= areaConhecimento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAreaConhecimento() {
		return areaConhecimento;
	}

	public void setAreaConhecimento(String areaConhecimento) {
		this.areaConhecimento = areaConhecimento;
	}

	@Override
	public String toString() {
		return "Curso [nome=" + nome + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Curso)) return false;
		Curso curso = (Curso) obj;
		return id != null && id.equals(curso.id);
	}

	@Override
	public int hashCode() {
		if (id != null) {
			return id.hashCode();
		}else {
			return 0;
		}
	} 
	
	
	
}
