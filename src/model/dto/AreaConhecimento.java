package model.dto;


public class AreaConhecimento implements IEntity{
	private Integer id;
	private String nome;
	
	public AreaConhecimento(Integer areaId,String nome) {
		super();
		this.id= areaId;
		this.nome= nome;
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
		this.nome= nome;
	}
	@Override
	public String toString() {
		return "AreaConhecimento [nome=" + nome + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AreaConhecimento)) {
			return false;
		}
		AreaConhecimento other = (AreaConhecimento) obj;
		return this.id.equals(other.id);
	}
	@Override
	public int hashCode() {
		if (id != null) {
			return id;
		}
		else {
			return 0;
		}
	}
	
}
